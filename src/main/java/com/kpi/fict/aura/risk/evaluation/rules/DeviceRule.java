package com.kpi.fict.aura.risk.evaluation.rules;

import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import org.springframework.stereotype.Component;

@Component
public class DeviceRule implements Rule {

    @Override
    public int getWeight() {
        return 30;
    }

    @Override
    public Float evaluate(RiskContext context) {
        boolean newDevice = context.device().isNew();
        boolean fingerprintMismatch = context.device().fingerprintMatch() == false;
        int score = 0;
        if (newDevice) score += getWeight();
        if (fingerprintMismatch) score += getWeight();
        return (float) Math.min(score, getWeight());
    }

}