package com.kpi.fict.aura.risk.ingestion.gateway;

import com.kpi.fict.aura.risk.dto.Event;
import com.kpi.fict.aura.risk.mapper.EventMapper;
import com.kpi.fict.aura.risk.pipeline.RiskPipeline;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PipelineDispatcher implements EventGateway {

    private final RiskPipeline pipeline;
    private final EventMapper eventMapper;

    @Override
    public void forward(Event event) {
        pipeline.run(eventMapper.toRiskContext(event));
    }

}