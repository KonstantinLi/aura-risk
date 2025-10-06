package com.kpi.fict.aura.risk.dto;

import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import com.kpi.fict.aura.risk.mapper.EventVisitor;

import java.time.Instant;

public interface Event {

    Long userId();

    Instant timestamp();

    RiskContext accept(EventVisitor visitor);

}