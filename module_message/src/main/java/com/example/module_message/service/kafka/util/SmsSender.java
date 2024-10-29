package com.example.module_message.service.kafka.util;

import com.example.module_message.dto.Message;
import com.example.module_message.dto.SmsMessageImpl;
import org.springframework.stereotype.Component;

@Component
public class SmsSender implements MessageSender {
    @Override
//    public void send(String message) {
    public void send(Message message) {
        System.out.println("Sending SMS: " + message);
    }

    @Override
//    public boolean supports(String message) {
    public boolean supports(Message message) {
        if(message.getType().equals("Sms")) {
            return isSms((SmsMessageImpl) message);  // Логика для проверки, является ли сообщение SMS
        }
        return false;
    }

//    private boolean isSms(String message) {
//        // Логика проверки, является ли сообщение SMS
//        return message.toUpperCase().startsWith("SMS:");
//    }
    private boolean isSms(SmsMessageImpl message) {
        // Логика проверки, является ли сообщение SMS
        return message.getAddress().toUpperCase().startsWith("SMS");
    }
}



