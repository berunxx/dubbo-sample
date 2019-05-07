package cn.waynezw.order.service.impl;

import cn.waynezw.order.mapper.OrderMapper;
import cn.waynezw.order.pojo.Order;
import cn.waynezw.order.service.OrderService;
import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 18:00
 * @Description:
 */
@Service(version = "1.0.0")
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
