package com.sample.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sample.config.DistributedLockByZookeeper;
import com.sample.item.pojo.Item;
import com.sample.item.service.ItemService;
import com.sample.order.pojo.Order;
import com.sample.order.service.OrderService;
import com.sample.service.ClusterService;
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

    @Autowired
    private OrderService orderService;

    @Autowired
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
            distributedLock.releaseDistributedLock(PATH);
            e.printStackTrace();
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
