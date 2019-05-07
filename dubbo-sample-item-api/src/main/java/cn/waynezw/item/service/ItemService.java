package cn.waynezw.item.service;


import cn.waynezw.item.pojo.Item;

import java.util.List;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 17:57
 * @Description:
 */
public interface ItemService {
    boolean buyItem(int itemId, int amount);

    List<Item> findAll();

    Item findById(int id);

    void save(Item item);
}
