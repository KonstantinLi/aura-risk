package com.kpi.fict.aura.risk.ingestion.processor;

import com.kpi.fict.aura.risk.dto.Event;

public interface EventProcessor<T extends Event> {

    void process(T event);

}