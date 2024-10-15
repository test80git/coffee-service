package com.example.producerkafkatest.service.impl;


import com.example.core.CoffeeBeansEvent;
import com.example.producerkafkatest.dto.CreateProductDto;

import com.example.producerkafkatest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;


@Service
@RequiredArgsConstructor
public class ProductServiceSynchronizedImpl implements ProductService {

    private final KafkaTemplate<String, CoffeeBeansEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public String createProduct(CreateProductDto createProductDto) throws ExecutionException, InterruptedException {
        //TODO sve BD
//        String productId = UUID.randomUUID().toString();
        String productId = "0cf0bd3c-ef5c-4dd1-9ec7-62806046b9ac";
        CoffeeBeansEvent grainMessage = new CoffeeBeansEvent( createProductDto.getVariety()
                , createProductDto.getCountryOfOrigin()
                , createProductDto.getArabicaPercentage(),
               createProductDto.getRobustaPercentage(),
               createProductDto.getQuantityGrainBag()
       );

        //synchronous
        SendResult<String, CoffeeBeansEvent> result = kafkaTemplate
                .send("beans-topic",  grainMessage).get();

        LOGGER.info("Topic: {}", result.getRecordMetadata().topic());
        LOGGER.info("Partition: {}", result.getRecordMetadata().partition());
        LOGGER.info("Offset: {}", result.getRecordMetadata().offset());

        LOGGER.info("Return: {}", productId);

        return productId;
    }
}
