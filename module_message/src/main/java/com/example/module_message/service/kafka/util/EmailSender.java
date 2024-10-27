package com.example.module_message.service.kafka.util;

public class EmailSender implements MessageSender {
    @Override
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }

    @Override
    public boolean supports(String message) {
        return isEmail(message);  // Логика для проверки, является ли сообщение Email
    }

    private boolean isEmail(String message) {
        // Логика проверки, является ли сообщение Email
        return message.toUpperCase().startsWith("EMAIL:");
    }
}