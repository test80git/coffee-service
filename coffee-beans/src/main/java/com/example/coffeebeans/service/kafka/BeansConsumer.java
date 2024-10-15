package com.example.coffeebeans.service.kafka;

import com.example.coffeebeans.model.entity.ArrivalOfCoffeeBeans;
import com.example.coffeebeans.model.mapper.BeansMapper;
import com.example.coffeebeans.service.BeansService;
import com.example.core.CoffeeBeansEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = "beans-topic", groupId = "beans-group")
public class BeansConsumer {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final BeansService beansService;

    private final BeansMapper beansMapper;

    @KafkaHandler
    public void consume(CoffeeBeansEvent beansEvent) {
        LOGGER.info("Received event: {} ", beansEvent);

        ArrivalOfCoffeeBeans response = beansMapper.toResponse(beansEvent);
        LOGGER.info("Received response: {}, ", response);

        beansService.saveBeans(response);
    }
}
