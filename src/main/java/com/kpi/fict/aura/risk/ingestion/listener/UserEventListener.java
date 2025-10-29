package com.kpi.fict.aura.risk.ingestion.listener;

import com.kpi.fict.aura.risk.dto.UserEvent;
import com.kpi.fict.aura.risk.ingestion.processor.UserEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventListener implements EventListener<UserEvent> {

    private final UserEventProcessor processor;

    @Override
    @KafkaListener(topics = "user-events", groupId = "risk-engine")
    public void onEvent(UserEvent event) {
        processor.process(event);
    }

}