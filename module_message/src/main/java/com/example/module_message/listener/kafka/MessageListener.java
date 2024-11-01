package com.example.module_message.listener.kafka;


import com.example.core.messageSMS.MessageEvent;
import com.example.module_message.dto.Message;
import com.example.module_message.mapper.MessageMapper;
import com.example.module_message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = "${spring.kafka.product.topic}", groupId = "${spring.kafka.consumer.group-id}")
public class MessageListener {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final MessageService messageServiceImpl;

    private final MessageMapper mapper;

    @KafkaHandler
    public void handle(@Payload MessageEvent messageEvent,
                       @Header("myMessageId") String myMessageId,
                       @Header(KafkaHeaders.RECEIVED_KEY) String messageKey) {

        LOGGER.info("Received event address: {} ", messageEvent.address());
        LOGGER.info("Received event payload: {} ", messageEvent.payload());
        LOGGER.info("Received event myMessageId: {} ", myMessageId);
        LOGGER.info("Received event messageKey: {} ", messageKey);

        Message message = mapper.mapToMessage(messageEvent);
        LOGGER.info("message => {}", message.getType());

        messageServiceImpl.processMessage(message);
    }


}
