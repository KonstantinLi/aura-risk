package com.kpi.fict.aura.risk.ingestion.processor;

import com.kpi.fict.aura.risk.dto.UserEvent;
import com.kpi.fict.aura.risk.ingestion.gateway.PipelineDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventProcessor implements EventProcessor<UserEvent> {

    private final PipelineDispatcher dispatcher;

    @Override
    public void process(UserEvent event) {
        dispatcher.forward(event);
    }

}