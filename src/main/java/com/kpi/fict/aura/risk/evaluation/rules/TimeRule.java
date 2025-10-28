package com.kpi.fict.aura.risk.evaluation.rules;

import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class TimeRule implements Rule {

    @Override
    public int getWeight() {
        return 10;
    }

    @Override
    public Float evaluate(RiskContext context) {
        int hour = context.timestamp().atZone(ZoneId.systemDefault()).getHour();
        if (hour < 5 || hour >= 23) return (float) getWeight();
        if (hour < 7 || hour > 21) return getWeight() / 2f;
        return 0f;
    }

}