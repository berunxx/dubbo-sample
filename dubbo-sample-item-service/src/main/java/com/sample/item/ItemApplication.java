package com.sample.item;


import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 23:01
 * @Description:
 */
@SpringBootApplication
@ImportResource("classpath:dubbo/provider.xml")
//@EnableDubbo
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }
}
