package com.kpi.fict.aura.risk.publish;

import com.kpi.fict.aura.risk.dto.StepUpEvent;
import com.kpi.fict.aura.risk.dto.RiskSignalType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StepUpNotifier {

    private static final String DESTINATION_TEMPLATE = "/topic/step-up/%d";

    private final SimpMessagingTemplate messagingTemplate;

    public void notify(StepUpEvent event) {
        log.warn("STEP-UP Authentication required for user {} (score={}, reason={})",
                event.userId(), event.score(), event.reason());
        messagingTemplate.convertAndSend(DESTINATION_TEMPLATE.formatted(event.userId()), StepUpPayload.from(event));
    }

    public record StepUpPayload(Long userId, double score, RiskSignalType reason) {
        public static StepUpPayload from(StepUpEvent e) {
            return new StepUpPayload(e.userId(), e.score(), e.reason());
        }
    }

}