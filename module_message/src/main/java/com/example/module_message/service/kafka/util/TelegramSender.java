package com.example.module_message.service.kafka.util;

import com.example.module_message.dto.Message;
import com.example.module_message.dto.TelegramMessageImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class TelegramSender implements MessageSender{
    @Override
    public void send(Message message) {
        System.out.println("Sending Telegram: " + message);
    }

    @Override
    public boolean supports(Message message) {
        if(message.getType().equals("Telegram")) {
            return isTelegram((TelegramMessageImpl) message);// Логика для проверки, является ли сообщение Telegram
        }
        return false;
    }

    private boolean isTelegram(TelegramMessageImpl message) {
        // Логика проверки, является ли сообщение Telegram
        return message.getAddress().toUpperCase().startsWith("TELEGRAM");
    }
}
