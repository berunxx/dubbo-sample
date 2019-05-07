package cn.waynezw.service;

import cn.waynezw.item.pojo.Item;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 23:18
 * @Description:
 */
public interface ClusterService {

    boolean buyItem(int itemId, int amount);

    Item getItem(int id);
}
