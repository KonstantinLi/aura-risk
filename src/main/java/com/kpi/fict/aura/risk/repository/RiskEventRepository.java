package com.kpi.fict.aura.risk.repository;

import com.kpi.fict.aura.risk.dto.RiskScore;
import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import com.kpi.fict.aura.risk.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RiskEventRepository {

    private final EventMapper eventMapper;
    private final RiskEventJpaRepository jpaRepository;

    public void saveEvent(RiskContext context, RiskScore score) {
        jpaRepository.save(eventMapper.toRiskEvent(context, score));
    }

}