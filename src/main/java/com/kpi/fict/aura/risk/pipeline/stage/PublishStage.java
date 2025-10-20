package com.kpi.fict.aura.risk.pipeline.stage;

import com.kpi.fict.aura.risk.dto.RiskScore;
import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import com.kpi.fict.aura.risk.publish.RiskAlertPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Step 4: Publish events to Kafka and send step-up MFA
 */
@Component
@RequiredArgsConstructor
public class PublishStage {

    private final RiskAlertPublisher publisher;

    public void process(RiskContext ctx, RiskScore score) {
        publisher.publish(ctx, score);
    }

}