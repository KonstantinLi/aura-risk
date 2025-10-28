package com.kpi.fict.aura.risk.evaluation.rules;

import com.kpi.fict.aura.risk.ingestion.context.RiskContext;

public interface Rule {

    int getWeight();

    Float evaluate(RiskContext context);

}