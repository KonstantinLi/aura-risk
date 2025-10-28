package com.kpi.fict.aura.risk.evaluation;

import com.kpi.fict.aura.risk.dto.RiskScore;
import com.kpi.fict.aura.risk.evaluation.rules.Rule;
import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import com.kpi.fict.aura.risk.model.RiskLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RiskEvaluator {

    private final List<Rule> rules;

    public RiskScore evaluate(RiskContext context) {
        Float total = rules.stream().map(rule -> rule.evaluate(context)).reduce(0f, Float::sum);
        return new RiskScore(total, byRiskRate(total));
    }

    private RiskLevel byRiskRate(Float rate) {
        return rate >= 70 ? RiskLevel.HIGH : (rate >= 30 ? RiskLevel.MEDIUM : RiskLevel.LOW);
    }

}