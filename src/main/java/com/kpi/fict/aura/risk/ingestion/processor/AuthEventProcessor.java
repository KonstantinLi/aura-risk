package com.kpi.fict.aura.risk.ingestion.processor;

import com.kpi.fict.aura.risk.dto.AuthEvent;
import com.kpi.fict.aura.risk.ingestion.gateway.PipelineDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthEventProcessor implements EventProcessor<AuthEvent> {

    private final PipelineDispatcher dispatcher;

    @Override
    public void process(AuthEvent event) {
        dispatcher.forward(event);
    }

}