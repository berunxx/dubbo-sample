package cn.waynezw.controller;

import cn.waynezw.item.pojo.Item;
import cn.waynezw.service.ClusterService;
import cn.waynezw.util.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 23:13
 * @Description:
 */
@RestController
@RequestMapping("/buy")
@Slf4j
public class BuyController {

    @Autowired
    private ClusterService clusterService;

    @PostMapping("/item/{itemId}")
    public JSONResult createOrder(@PathVariable int itemId, @RequestBody Item item) {
        boolean b = clusterService.buyItem(itemId, item.getAmount());
        return b? JSONResult.ok("购买成功", null) : JSONResult.error(510, "数量不足");
    }

    @PostMapping("/item2/{itemId}")
    public JSONResult createOrder2(@PathVariable int itemId, @RequestBody Item item) {
        boolean b = clusterService.buyItem(itemId, item.getAmount());
        return b? JSONResult.ok("购买成功", null) : JSONResult.error(510, "数量不足");
    }
}
