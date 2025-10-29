package com.kpi.fict.aura.risk.ingestion.context;

import java.time.Instant;
import java.util.Map;

public record RiskContext(
        Long userId,
        String deviceId,
        String sessionId,
        Instant timestamp,
        Float previousScore,
        Map<String, Object> attributes) {

    public void put(String key, Object value) {
        attributes.put(key, value);
    }

    public Object get(String key) {
        return attributes.get(key);
    }

}