package com.example.producerkafkatest.dto;

import java.util.Map;

public record MessageRequest(String address, Map<String, Object> payload) {
}
