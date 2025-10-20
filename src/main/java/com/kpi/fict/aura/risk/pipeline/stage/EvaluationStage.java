package com.kpi.fict.aura.risk.pipeline.stage;

import com.kpi.fict.aura.risk.evaluation.RiskEvaluator;
import com.kpi.fict.aura.risk.dto.RiskScore;
import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Step 2: Apply risk calculation rules
 */
@Component
@RequiredArgsConstructor
public class EvaluationStage {

    private final RiskEvaluator riskEvaluator;

    public RiskScore process(RiskContext ctx) {
        return riskEvaluator.evaluate(ctx);
    }

}