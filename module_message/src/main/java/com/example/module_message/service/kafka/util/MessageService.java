package com.example.module_message.service.kafka.util;

import com.example.module_message.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final List<MessageSender> senders;

//    public void processMessage(String message) {
//        for (MessageSender sender : senders) {
//            if (sender.supports(message)) {
//                sender.send(message);
//                return;
//            }
//        }
//        throw new UnsupportedOperationException("Message type not supported");
//    }

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
