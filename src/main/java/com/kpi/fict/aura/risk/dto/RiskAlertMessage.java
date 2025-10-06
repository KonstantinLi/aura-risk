package com.kpi.fict.aura.risk.dto;

import com.kpi.fict.aura.risk.model.RiskLevel;

import java.time.LocalDateTime;

public record RiskAlertMessage(
        Long userId,
        Long deviceId,
        String geo,
        Float score,
        RiskLevel level,
        LocalDateTime timestamp) {}