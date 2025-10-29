package com.kpi.fict.aura.risk.ingestion.listener;

import com.kpi.fict.aura.risk.dto.AuthEvent;
import com.kpi.fict.aura.risk.ingestion.processor.AuthEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthEventListener implements EventListener<AuthEvent> {

    private final AuthEventProcessor processor;

    @Override
    @KafkaListener(topics = "auth-events", groupId = "risk-engine")
    public void onEvent(AuthEvent event) {
        processor.process(event);
    }

}