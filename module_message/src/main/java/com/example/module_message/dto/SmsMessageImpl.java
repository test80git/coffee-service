package com.example.module_message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
@Component
public class SmsMessageImpl implements Message {
    private String address;

    private String content;

    private OffsetDateTime timestamp;

    @Override
    public String getType() {
        return "Sms";
    }
}
