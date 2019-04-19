package com.sample.order.service.impl;

import com.sample.order.mapper.OrderMapper;
import com.sample.order.pojo.Order;
import com.sample.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 18:00
 * @Description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public boolean buyItem(String itemId) {
        log.info("购买商品 id：" + itemId);
        return false;
    }

    @Override
    public List<Order> findAll() {
        List<Order> all = orderMapper.findAll();
        return all;
    }
    @Override
    public Order findById(int id) {
        return orderMapper.findById(id);
    }

    @Override
    public int createOrder(Order order) {
        int i = orderMapper.create(order);
        return i;
    }
}
