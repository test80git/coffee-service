package com.example.module_message.sender.impl;

import com.example.module_message.dto.Message;
import com.example.module_message.sender.MessageSender;
import org.springframework.stereotype.Component;

@Component
public class TelegramSender implements MessageSender {
    @Override
    public void send(Message message) {
        System.out.println("Sending Telegram: " + message);
    }

    @Override
    public boolean supports(Message message) {
        return message.getType().equals("Telegram");// Логика для проверки, является ли сообщение Telegram
    }
}
