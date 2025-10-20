package com.kpi.fict.aura.risk.pipeline.stage;

import com.kpi.fict.aura.risk.dto.RiskScore;
import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import com.kpi.fict.aura.risk.repository.RiskEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Step 3: save the risk event (history, audit)
 */
@Component
@RequiredArgsConstructor
public class PersistenceStage {

    private final RiskEventRepository riskEventRepository;

    public void process(RiskContext context, RiskScore score) {
        riskEventRepository.saveEvent(context, score);
    }

}