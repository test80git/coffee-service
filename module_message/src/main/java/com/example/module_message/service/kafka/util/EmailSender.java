package com.example.module_message.service.kafka.util;

import com.example.module_message.dto.EmailMessageImpl;
import com.example.module_message.dto.Message;
import org.springframework.stereotype.Component;

@Component
public class EmailSender implements MessageSender {
//    @Override
//    public void send(String message) {
//        System.out.println("Sending Email: " + message);
//    }
//
//    @Override
//    public boolean supports(String message) {
//        return isEmail(message);  // Логика для проверки, является ли сообщение Email
//    }
//
//    private boolean isEmail(String message) {
//        // Логика проверки, является ли сообщение Email
//        return message.toUpperCase().startsWith("EMAIL:");
//    }


    @Override
    public void send(Message message) {
        System.out.println("Sending Email: " + message);
    }

    @Override
    public boolean supports(Message message) {
        if (message.getType().equals("Email")) {
            return isEmail((EmailMessageImpl) message);
        } // Логика для проверки, является ли сообщение Email
        return false;
    }

    private boolean isEmail(EmailMessageImpl message) {
        // Логика проверки, является ли сообщение Email
        return message.getAddress().toUpperCase().startsWith("EMAIL");
    }
}