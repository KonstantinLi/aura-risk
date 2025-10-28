package com.kpi.fict.aura.risk.evaluation.rules;

import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import org.springframework.stereotype.Component;

@Component
public class AttemptRule implements Rule {

    @Override
    public int getWeight() {
        return 20;
    }

    @Override
    public Float evaluate(RiskContext context) {
        int fails = context.auth().failedAttempts();
        if (fails >= 5) return (float) getWeight();
        if (fails >= 2) return getWeight / 2f;
        return 0f;
    }

}