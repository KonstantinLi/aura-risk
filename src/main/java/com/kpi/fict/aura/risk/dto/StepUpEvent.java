package com.kpi.fict.aura.risk.dto;

public record StepUpEvent(
        Long userId,
        Long deviceId,
        RiskSignalType reason,
        Float score) {}