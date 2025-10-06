package com.kpi.fict.aura.risk.dto;

import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import com.kpi.fict.aura.risk.mapper.EventVisitor;

import java.time.Instant;

public record AuthEvent(
        Long userId,
        String stage,
        boolean success,
        String ip,
        String deviceId,
        Instant timestamp) implements Event {

    @Override
    public RiskContext accept(EventVisitor visitor) {
        return visitor.visit(this);
    }

}