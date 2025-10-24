package com.kpi.fict.aura.risk.publish;

import com.kpi.fict.aura.risk.dto.RiskScore;
import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import com.kpi.fict.aura.risk.mapper.EventMapper;
import com.kpi.fict.aura.risk.model.RiskLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RiskAlertPublisher {

    private final EventMapper eventMapper;
    private final StepUpNotifier notifier;
    private final KafkaRiskProducer producer;

    public void publish(RiskContext context, RiskScore score) {
        producer.publish(eventMapper.toRiskAlertMessage(context, score));
        if (score.level() != RiskLevel.LOW) {
            notifier.notify(eventMapper.toStepUpEvent(context, score));
        }
    }

}