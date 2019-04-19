package com.sample;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-16 18:39
 * @Description:
 */
@SpringBootApplication
//@EnableDubbo
@ImportResource("classpath:dubbo/consumer.xml")
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
