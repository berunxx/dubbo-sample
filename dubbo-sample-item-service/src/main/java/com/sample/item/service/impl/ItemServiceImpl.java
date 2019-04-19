package com.sample.item.service.impl;

import com.sample.item.mapper.ItemMapper;
import com.sample.item.pojo.Item;
import com.sample.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 22:53
 * @Description:
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Override
    public boolean buyItem(int itemId, int amount) {
        Item item = new Item();
        item.setId(itemId);
        item.setAmount(amount);
        itemMapper.buyItem(item);
        return true;
    }

    @Override
    public List<Item> findAll() {
        return itemMapper.findAll();
    }

    @Override
    public Item findById(int id) {
        return itemMapper.findById(id);
    }

    @Override
    public void save(Item item) {
        itemMapper.save(item);
    }
}
