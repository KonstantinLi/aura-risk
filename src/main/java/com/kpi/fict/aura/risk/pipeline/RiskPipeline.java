package com.kpi.fict.aura.risk.pipeline;

import com.kpi.fict.aura.risk.dto.RiskScore;
import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import com.kpi.fict.aura.risk.pipeline.stage.EnrichmentStage;
import com.kpi.fict.aura.risk.pipeline.stage.EvaluationStage;
import com.kpi.fict.aura.risk.pipeline.stage.PersistenceStage;
import com.kpi.fict.aura.risk.pipeline.stage.PublishStage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RiskPipeline {

    private final PublishStage publish;
    private final EnrichmentStage enrichment;
    private final EvaluationStage evaluation;
    private final PersistenceStage persistence;

    public RiskScore run(RiskContext context) {
        RiskContext enriched = enrichment.process(context);
        RiskScore score = evaluation.process(enriched);
        persistence.process(enriched, score);
        publish.process(enriched, score);
        return score;
    }

}