package com.kpi.fict.aura.risk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "behavior_event")
public class BehaviorEvent extends BaseEntity {

    private Long sessionId;

    private Long userId;

    private Float mouseSpeed;

    private Float typingSpeed;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private BehaviorEventType type;

    public Float getSpeed() {
        return getType() == BehaviorEventType.MOUSE ? mouseSpeed : typingSpeed;
    }

}