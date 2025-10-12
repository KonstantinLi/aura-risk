package com.kpi.fict.aura.risk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "device_fingerprint")
public class DeviceFingerprint extends BaseEntity {

    private Long userId;

    private String deviceIdHash;

    private String os;

    private String browser;

    private boolean trusted;

}