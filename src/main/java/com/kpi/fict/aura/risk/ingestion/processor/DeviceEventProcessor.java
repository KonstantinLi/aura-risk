package com.kpi.fict.aura.risk.ingestion.processor;

import com.kpi.fict.aura.risk.dto.DeviceEvent;
import com.kpi.fict.aura.risk.ingestion.gateway.PipelineDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeviceEventProcessor implements EventProcessor<DeviceEvent> {

    private final PipelineDispatcher dispatcher;

    @Override
    public void process(DeviceEvent event) {
        dispatcher.forward(event);
    }

}