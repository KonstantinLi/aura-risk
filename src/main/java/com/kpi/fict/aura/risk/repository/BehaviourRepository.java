package com.kpi.fict.aura.risk.repository;

import com.kpi.fict.aura.risk.model.BehaviorEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BehaviourRepository extends JpaRepository<BehaviorEvent, Long> {

    List<BehaviorEvent> findTop20ByUserIdOrderByTimestampDesc(Long userId);

}