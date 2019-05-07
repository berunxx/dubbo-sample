package cn.waynezw.service.impl;

import cn.waynezw.config.zookeeper.DistributedLockByZookeeper;
import cn.waynezw.item.pojo.Item;
import cn.waynezw.item.service.ItemService;
import cn.waynezw.order.pojo.Order;
import cn.waynezw.order.service.OrderService;
import cn.waynezw.service.ClusterService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 23:18
 * @Description:
 */
@Service
@Slf4j
public class ClusterServiceImpl implements ClusterService {

    @Reference(version = "1.0.0")
    private OrderService orderService;

    @Reference(version = "1.0.0")
    private ItemService itemService;

    @Autowired
    private DistributedLockByZookeeper distributedLock;

    private final static String PATH = "order";


    @Override
    public boolean buyItem(int id, int amount) {
        distributedLock.acquireDistributedLock(PATH);
        Item item = itemService.findById(id);
        log.info("查找订单");
        log.info("商品【{}】扣减库存，剩余库存{},扣减库存{}", item.getName(), item.getAmount(),amount);
        if (item.getAmount() < amount) {
            log.info("订单数量不足：" + item.getAmount());
            distributedLock.releaseDistributedLock(PATH);
            return false;
        }
        try {
            log.info("业务处理");
            // 模拟业务处理时间
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("业务处理异常：", e);
        } finally {
            distributedLock.releaseDistributedLock(PATH);
        }
        itemService.buyItem(item.getId(), amount);
        log.info("扣减库存成功");
        Order order = new Order();
        order.setItemId(item.getId());
        order.setOrderNo(UUID.randomUUID().toString());
        orderService.createOrder(order);
        log.info("生成订单完成");
        distributedLock.releaseDistributedLock(PATH);
        return true;
    }

    @Override
    public Item getItem(int id) {
        Item byId = itemService.findById(id);
        return byId;
    }
}
