package com.example.module_message.sender.impl;

import com.example.module_message.dto.Message;
import com.example.module_message.sender.MessageSender;
import org.springframework.stereotype.Component;

@Component
public class SmsSender implements MessageSender {
    @Override
    public void send(Message message) {
        System.out.println("Sending SMS: " + message);
    }

    @Override
    public boolean supports(Message message) {
        return message.getType().equals("Sms"); // Логика для проверки, является ли сообщение SMS
    }
}



