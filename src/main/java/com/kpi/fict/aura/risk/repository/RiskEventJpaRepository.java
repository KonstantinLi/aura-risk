package com.kpi.fict.aura.risk.repository;

import com.kpi.fict.aura.risk.model.RiskEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskEventJpaRepository extends JpaRepository<RiskEvent, Long> {}