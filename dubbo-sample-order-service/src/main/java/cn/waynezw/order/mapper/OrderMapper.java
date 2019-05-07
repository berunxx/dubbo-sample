package cn.waynezw.order.mapper;

import cn.waynezw.order.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 18:04
 * @Description:
 */
@Mapper
public interface OrderMapper {
    Order findById(Integer id);

    void deleteById(Integer id);

    List<Order> findAll();

    int create(Order order);
}
