package com.kpi.fict.aura.risk.ingestion.listener;

import com.kpi.fict.aura.risk.dto.Event;

public interface EventListener<T extends Event> {

    void onEvent(T event);

}
