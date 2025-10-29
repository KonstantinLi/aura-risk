package com.kpi.fict.aura.risk.ingestion.listener;

import com.kpi.fict.aura.risk.dto.DeviceEvent;
import com.kpi.fict.aura.risk.ingestion.processor.DeviceEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeviceEventListener implements EventListener<DeviceEvent> {

    private final DeviceEventProcessor processor;

    @Override
    @KafkaListener(topics = "device-events", groupId = "risk-engine")
    public void onEvent(DeviceEvent event) {
        processor.process(event);
    }

}