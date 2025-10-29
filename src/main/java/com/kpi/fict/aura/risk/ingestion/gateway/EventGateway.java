package com.kpi.fict.aura.risk.ingestion.gateway;

import com.kpi.fict.aura.risk.dto.Event;

public interface EventGateway {

    void forward(Event event);

}