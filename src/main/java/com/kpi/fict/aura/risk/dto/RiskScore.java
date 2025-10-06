package com.kpi.fict.aura.risk.dto;

import com.kpi.fict.aura.risk.model.RiskLevel;

public record RiskScore(Float score, RiskLevel level) {}