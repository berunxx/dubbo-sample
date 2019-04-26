package com.sample.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @KafkaListener(topics = "logger-channel", groupId = "group-id")
    public void listen(String message) {
        log.info(String.format("Consumed Message -> %s", message));
    }
}