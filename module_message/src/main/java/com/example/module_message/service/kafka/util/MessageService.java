package com.example.module_message.service.kafka.util;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final List<MessageSender> senders;

    public MessageService(List<MessageSender> senders) {
        this.senders = senders;
    }

    public void processMessage(String message) {
        for (MessageSender sender : senders) {
            if (sender.supports(message)) {
                sender.send(message);
                return;
            }
        }
        throw new UnsupportedOperationException("Message type not supported");
    }
}
