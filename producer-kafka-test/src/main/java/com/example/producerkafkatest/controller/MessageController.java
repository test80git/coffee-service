package com.example.producerkafkatest.controller;

import com.example.producerkafkatest.dto.MessageRequest;
import com.example.producerkafkatest.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @PostMapping()
    public ResponseEntity<String> sendMessageKafka(@RequestBody MessageRequest message) throws ExecutionException, InterruptedException {
        LOGGER.info("@RequestBody MessageRequest {}", message);
        String s = messageService.sendKafka(message);
        return ResponseEntity.ok(s);
    }
}
