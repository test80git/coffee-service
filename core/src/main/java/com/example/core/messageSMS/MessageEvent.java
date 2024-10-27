package com.example.core.messageSMS;

import java.util.Map;

public record MessageEvent(String address, Map<String, Object> payload) {
}
