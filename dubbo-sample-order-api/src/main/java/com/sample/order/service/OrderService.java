package com.sample.order.service;

import com.sample.order.pojo.Order;

import java.util.List;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 17:57
 * @Description:
 */
public interface OrderService {
    boolean buyItem(String itemId);

    List<Order> findAll();

    Order findById(int id);

    int createOrder(Order order);
}
