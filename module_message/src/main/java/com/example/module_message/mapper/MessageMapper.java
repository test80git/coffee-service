package com.example.module_message.mapper;

import com.example.core.messageSMS.MessageEvent;
import com.example.module_message.dto.EmailMessage;
import com.example.module_message.dto.Message;
import com.example.module_message.dto.SmsMessage;
import com.example.module_message.dto.TelegramMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.util.Map;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageMapper {

    @Mapping(target = "content", source = "payload.content", qualifiedByName = "mapToString")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "timestamp", expression = "java(mapToCurrentTimestamp(messageEvent.payload().get(\"timestamp\")))")
    SmsMessage toSmsMessage(MessageEvent messageEvent);

    @Mapping(target = "content", source = "payload.content", qualifiedByName = "mapToString")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "timestamp", expression = "java(mapToCurrentTimestamp(messageEvent.payload().get(\"timestamp\")))")
    TelegramMessage toTelegramMessage(MessageEvent messageEvent);

    @Mapping(target = "subject", source = "payload.subject", qualifiedByName = "mapToString")
    @Mapping(target = "body", source = "payload.body", qualifiedByName = "mapToString")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "timestamp", expression = "java(mapToCurrentTimestamp(messageEvent.payload().get(\"timestamp\")))")
    EmailMessage toEmailMessage(MessageEvent messageEvent);

    default Message mapToMessage(MessageEvent messageEvent) {
        Map<String, Object> payload = messageEvent.payload();
        if (payload.containsKey("subject") && payload.containsKey("body")) {
            // Map to EmailMessageImpl
            return toEmailMessage(messageEvent);
        } else if (payload.containsKey("content")) {
            if (messageEvent.address().toUpperCase().startsWith("TELEGRAM")) {
                return toTelegramMessage(messageEvent);
            } else if (messageEvent.address().toUpperCase().startsWith("SMS"))
                // Map to SmsMessageImpl
                return toSmsMessage(messageEvent);
        }
        throw new IllegalArgumentException("Unknown message type in payload: " + payload);
    }

    @Named("mapToString")
    default String mapToString(Object value) {
        return value != null ? value.toString() : null;
    }

    default OffsetDateTime mapToCurrentTimestamp(Object timestamp) {
        return timestamp instanceof String ? OffsetDateTime.parse((String) timestamp) : OffsetDateTime.now();
    }
}

