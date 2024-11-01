package com.example.module_message.service.impl;

import com.example.module_message.dto.Message;
import com.example.module_message.sender.MessageSender;
import com.example.module_message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final List<MessageSender> senders;

    public void processMessage(Message message) {
        for (MessageSender sender : senders) {
            if (sender.supports(message)) {
                sender.send(message);
                return;
            }
        }
        throw new UnsupportedOperationException("Message type not supported");
    }
}
