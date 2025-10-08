package com.kpi.fict.aura.risk.enrich;

import com.kpi.fict.aura.risk.model.UserRiskProfile;
import com.kpi.fict.aura.risk.repository.UserRiskProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProfileCache {

    private final UserRiskProfileRepository repository;

    @Transactional(readOnly = true)
    public Optional<Float> getLastRiskScore(Long userId) {
        return repository.findById(userId).map(UserRiskProfile::getLastRiskScore);
    }

    @Transactional
    public void updateRiskScore(Long userId, Float score, String geo, Long deviceId) {
        repository.save(UserRiskProfile.builder()
                .lastGeo(geo)
                .userId(userId)
                .lastRiskScore(score)
                .lastDeviceId(deviceId)
                .lastEventTimestamp(Instant.now().toEpochMilli())
                .build()
        );
    }

}