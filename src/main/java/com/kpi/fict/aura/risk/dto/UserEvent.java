package com.kpi.fict.aura.risk.dto;

import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import com.kpi.fict.aura.risk.mapper.EventVisitor;

import java.time.Instant;

public record UserEvent(
        Long userId,
        String action,
        String ip,
        String deviceId,
        Instant timestamp) implements Event {

    @Override
    public RiskContext accept(EventVisitor visitor) {
        return visitor.visit(this);
    }

}