package com.kpi.fict.aura.risk.enrich;

import com.kpi.fict.aura.risk.model.BehaviorEvent;
import com.kpi.fict.aura.risk.model.BehaviorEventType;
import com.kpi.fict.aura.risk.repository.BehaviourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BehaviorTap {

    private static final int MIN_EVENTS_FOR_ANALYSIS = 5;

    private final BehaviourRepository behaviourRepository;

    public record BehaviorProfile(
            float avgKeystrokeInterval,
            float avgMouseVelocity,
            float gestureStability,
            float reactionTime,
            long timestamp) {}

    /**
     * Aggregate behavior and calculate a “behavior risk factor”.
     */
    public float evaluateBehaviorRisk(Long userId, BehaviorProfile previousProfile) {
        List<BehaviorEvent> events = behaviourRepository.findTop20ByUserIdOrderByTimestampDesc(userId);
        if (events.size() < MIN_EVENTS_FOR_ANALYSIS) return 0.0f; // not enough data → neutral effect
        if (previousProfile == null) return 10.0f; // first entry → small increase in risk
        BehaviorProfile current = aggregate(events);

        // Generate deviation score (0–100)
        float deviation = 0f;
        deviation += difference(previousProfile.avgKeystrokeInterval(), current.avgKeystrokeInterval()) * 0.35f;
        deviation += difference(previousProfile.avgMouseVelocity(), current.avgMouseVelocity()) * 0.30f;
        deviation += difference(previousProfile.gestureStability(), current.gestureStability()) * 0.25f;
        deviation += difference(previousProfile.reactionTime(), current.reactionTime()) * 0.10f;
        deviation = Math.min(100f, deviation);

        // 40% → HIGH behavior anomaly
        return deviation;
    }

    private BehaviorProfile aggregate(List<BehaviorEvent> events) {
        float gestures = avg(events, BehaviorEventType.GESTURE);
        float avgMouseVel = avg(events, BehaviorEventType.MOUSE);
        float avgKeystroke = avg(events, BehaviorEventType.KEYSTROKE);

        LocalDateTime first = events.get(0).getTimestamp();
        LocalDateTime last = events.get(events.size() - 1).getTimestamp();
        float reaction = Duration.between(first, last).toMillis();

        return new BehaviorProfile(
                avgKeystroke,
                avgMouseVel,
                gestures,
                reaction,
                Instant.now().toEpochMilli());
    }

    private float avg(List<BehaviorEvent> list, BehaviorEventType type) {
        return (float) list.stream()
                .filter(event -> event.getType() == type)
                .mapToDouble(BehaviorEvent::getSpeed)
                .average()
                .orElse(0.0);
    }

    private float difference(float base, float current) {
        if (base == 0 && current == 0) return 0;
        if (base == 0) return 100f;
        float diff = Math.abs(current - base) / base * 100f;
        return Math.min(diff, 100f);
    }

    public BehaviorProfile createProfile(List<BehaviorEvent> events) {
        return aggregate(events);
    }

}