package com.example.module_message.service.kafka.util;

public class SmsSender implements MessageSender {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }

    @Override
    public boolean supports(String message) {
        return isSms(message);  // Логика для проверки, является ли сообщение SMS
    }

    private boolean isSms(String message) {
        // Логика проверки, является ли сообщение SMS
        return message.toUpperCase().startsWith("SMS:");
    }
}



