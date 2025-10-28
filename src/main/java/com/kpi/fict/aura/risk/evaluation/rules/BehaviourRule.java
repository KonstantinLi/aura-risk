package com.kpi.fict.aura.risk.evaluation.rules;

import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import org.springframework.stereotype.Component;

@Component
public class BehaviourRule implements Rule {

    @Override
    public int getWeight() {
        return 20;
    }

    @Override
    public Float evaluate(RiskContext context) {
        if (context.behavior() == null) return 0f;
        if (context.behavior().typingDeviation() > 0.4 || context.behavior().mouseDeviation() > 0.4) return (float) getWeight();
        return 0f;
    }

}