package com.kpi.fict.aura.risk.pipeline.stage;

import com.kpi.fict.aura.risk.enrich.BehaviorTap;
import com.kpi.fict.aura.risk.enrich.ProfileCache;
import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Step 1: Enrich the input context with data from caches, repositories and etc
 */
@Component
@RequiredArgsConstructor
public class EnrichmentStage {

    private final BehaviorTap behaviorTap;
    private final ProfileCache profileCache;

    public RiskContext process(RiskContext context) {
        return context.with(
                profileCache.getLastRiskScore(context.userId()),
                behaviorTap.evaluateBehaviorRisk(
                        context.userId(),
                        (BehaviorTap.BehaviorProfile) context.get("behaviour"))
        );
    }

}