package cn.waynezw.item.mapper;

import cn.waynezw.item.pojo.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 22:54
 * @Description:
 */
@Mapper
public interface ItemMapper {

    /**
     * 根据主键查找Item
     * @param id 主键ID
     * @return Item
     */
    Item findById(int id);

    /**
     * 删除
     * @param id 主键
     */
    void deleteById(String id);

    List<Item> findAll();

    Integer buyItem(Item item);

    void save(Item item);
}
