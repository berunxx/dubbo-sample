package com.sample.controller;

import com.sample.item.pojo.Item;
import com.sample.service.ClusterService;
import com.sample.util.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 23:13
 * @Description:
 */
@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {

    @Autowired
    private ClusterService clusterService;

    @GetMapping("/{itemId}")
    public JSONResult getItem(@PathVariable Integer itemId) {
        log.info("get item by id {}", itemId);
        Item item = clusterService.getItem(itemId);
        return JSONResult.ok(item);
    }
}
