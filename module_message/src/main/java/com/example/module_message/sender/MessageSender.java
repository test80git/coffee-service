package com.example.module_message.sender;

import com.example.module_message.dto.Message;

public interface MessageSender {

//    void send(String message);
    void send(Message message);

//    boolean supports(String message);
    boolean supports(Message message);
}