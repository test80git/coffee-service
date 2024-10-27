package com.example.producerkafkatest.service;

import com.example.core.messageSMS.MessageEvent;
import com.example.producerkafkatest.dto.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final KafkaTemplate<String, MessageEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public String sendKafka(MessageRequest message) throws ExecutionException, InterruptedException {
        //TODO sve BD
        String messageId = UUID.randomUUID().toString();
        MessageEvent messageEvent = new MessageEvent(message.address(), message.payload());

        ProducerRecord<String, MessageEvent> record = new ProducerRecord<>(
                "message-topic",
                messageId,
                messageEvent
        );

        record.headers().add("myMessageId", UUID.randomUUID().toString().getBytes());

        SendResult<String, MessageEvent> result =
                kafkaTemplate.send(record).get();

        LOGGER.info("Topic: {}", result.getRecordMetadata().topic());
        LOGGER.info("Partition: {}", result.getRecordMetadata().partition());
        LOGGER.info("Offset: {}", result.getRecordMetadata().offset());

        LOGGER.info("Return: {}", messageId);
        return messageId;
    }
}
