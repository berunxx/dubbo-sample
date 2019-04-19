package com.sample.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-20 10:39
 * @Description: Zookeeper 实现分布式锁
 */

@Slf4j
@Service
public class DistributedLockByZookeeper implements InitializingBean {

    private static final String ROOT_PATH_LOCK = "root-lock";
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Autowired
    private CuratorFramework curatorFramework;

    /**
     * 获取分布式锁
     *
     * @param path
     */
    public void acquireDistributedLock(String path) {
        String keyPath = "/" + ROOT_PATH_LOCK + "/" + path;
        while (true) {
            try {
                curatorFramework
                        .create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(keyPath);
                // 临时节点
                log.info("成功获取到锁，路径为:{}", keyPath);
                break;
            } catch (Exception e) {
                log.info("没有获取到锁，路径为:{}", keyPath);
                log.info("重新获取锁 .......");
                try {
                    if (countDownLatch.getCount() <= 0) {
                        countDownLatch = new CountDownLatch(1);
                    }
                    countDownLatch.await();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 释放分布式锁
     *
     * @param path
     * @return
     */
    public boolean releaseDistributedLock(String path) {
        try {
            String keyPath = "/" + ROOT_PATH_LOCK + "/" + path;
            // 分布式锁是否存在
            if (curatorFramework.checkExists().forPath(keyPath) != null) {
                // 删除节点
                curatorFramework.delete().forPath(keyPath);
            }
        } catch (Exception e) {
            log.error("锁释放失败");
            return false;
        }
        return true;
    }

    /**
     * 创建 watcher 事件
     */
    private void addWatcher(String path) throws Exception {
        String keyPath;
        if (path.equals(ROOT_PATH_LOCK)) {
            keyPath = "/" + path;
        } else {
            keyPath = "/" + ROOT_PATH_LOCK + "/" + path;
        }
        final PathChildrenCache cache = new PathChildrenCache(curatorFramework, keyPath, false);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener((client, event) -> {
            if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                String oldPath = event.getData().getPath();
                log.info("上一个节点 " + oldPath + " 已经被断开");
                if (oldPath.contains(path)) {
                    //释放计数器，让当前的请求获取锁
                    countDownLatch.countDown();
                }
            }
        });
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        curatorFramework = curatorFramework.usingNamespace("dubbo-sample-lock");
        String path = "/" + ROOT_PATH_LOCK;
        try {
            if (curatorFramework.checkExists().forPath(path) == null) {
                curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(path);
            }
            addWatcher(ROOT_PATH_LOCK);
            log.info("root path 的 watcher 事件创建成功");
        } catch (Exception e) {
            log.error("连接zookeeper失败 {}", e.getMessage(), e);
        }
    }
}
