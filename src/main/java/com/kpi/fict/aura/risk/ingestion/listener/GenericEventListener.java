package com.kpi.fict.aura.risk.ingestion.listener;

import com.kpi.fict.aura.risk.dto.RiskSignalEvent;
import com.kpi.fict.aura.risk.ingestion.processor.EventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenericEventListener implements EventListener<RiskSignalEvent> {

    private final EventProcessor<RiskSignalEvent> processor;

    @Override
    @KafkaListener(topics = "device-events", groupId = "risk-engine")
    public void onEvent(RiskSignalEvent event) {
        processor.process(event);
    }

}