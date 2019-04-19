package com.sample.order;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.List;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 18:05
 * @Description:
 */
@SpringBootApplication
@ImportResource("classpath:dubbo/provider.xml")
//@EnableDubbo
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}