package com.sample.item.mapper;

import com.sample.item.pojo.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 22:54
 * @Description:
 */
@Mapper
public interface ItemMapper {

    Item findById(int id);

    void deleteById(String id);

    List<Item> findAll();

    Integer buyItem(Item item);

    void save(Item item);
}
