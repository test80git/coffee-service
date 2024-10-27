package com.example.producerkafkatest.service;

import com.example.producerkafkatest.dto.MessageRequest;

import java.util.concurrent.ExecutionException;

public interface MessageService {

    String sendKafka(MessageRequest message) throws ExecutionException, InterruptedException;
}
