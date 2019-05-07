package cn.waynezw.item.service.impl;

import cn.waynezw.item.mapper.ItemMapper;
import cn.waynezw.item.pojo.Item;
import cn.waynezw.item.service.ItemService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 22:53
 * @Description:
 */
@Service(version = "1.0.0")
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
