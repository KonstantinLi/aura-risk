package com.kpi.fict.aura.risk.evaluation.rules;

import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import org.springframework.stereotype.Component;

@Component
public class GeoRule implements Rule {

    @Override
    public int getWeight() {
        return 20;
    }

    @Override
    public Float evaluate(RiskContext context) {
        if (context.geo() == null) return 0;

        boolean newCountry = context.geo().isNewCountry();
        boolean risky = context.geo().isRiskyCountry();
        boolean timeZoneJump = context.geo().timeZoneShift() > 3;

        int score = 0;

        if (newCountry) score += getWeight();
        if (risky) score += getWeight();
        if (timeZoneJump) score += getWeight() / 2;

        return (float) Math.min(score, getWeight());
    }

}