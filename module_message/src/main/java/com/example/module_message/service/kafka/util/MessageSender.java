package com.example.module_message.service.kafka.util;

public interface MessageSender {
    void send(String message);
    boolean supports(String message);
}
