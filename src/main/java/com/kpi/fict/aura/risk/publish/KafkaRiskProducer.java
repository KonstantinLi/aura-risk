package com.kpi.fict.aura.risk.publish;

import com.kpi.fict.aura.risk.dto.RiskAlertMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaRiskProducer {

    private static final String TOPIC = "risk.alerts";

    private final KafkaTemplate<String, Object> kafka;

    public void publish(RiskAlertMessage message) {
        kafka.send(TOPIC, message.userId().toString(), message);
    }

}