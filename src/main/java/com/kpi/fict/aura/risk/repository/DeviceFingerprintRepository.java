package com.kpi.fict.aura.risk.repository;

import com.kpi.fict.aura.risk.model.DeviceFingerprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceFingerprintRepository extends JpaRepository<DeviceFingerprint, Long> {

    List<DeviceFingerprint> findByUserId(Long userId);

    Optional<DeviceFingerprint> findByDeviceIdHash(String hash);

}