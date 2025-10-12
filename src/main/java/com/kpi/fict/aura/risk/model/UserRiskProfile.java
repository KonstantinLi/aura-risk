package com.kpi.fict.aura.risk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "user_risk_profile", timeToLive = 3600)
public class UserRiskProfile implements Serializable {

    private Long userId;

    private String lastGeo;

    private Float lastRiskScore;

    private Long lastDeviceId;

    private Long lastEventTimestamp;

}