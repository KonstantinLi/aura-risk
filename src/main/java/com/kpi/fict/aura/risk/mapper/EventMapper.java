package com.kpi.fict.aura.risk.mapper;

import com.kpi.fict.aura.risk.dto.*;
import com.kpi.fict.aura.risk.dto.RiskScore;
import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import com.kpi.fict.aura.risk.model.RiskEvent;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MappingConfig.class )
public abstract class EventMapper implements EventVisitor {

    public RiskContext toRiskContext(Event event) {
        return event.accept(this);
    }

    public abstract RiskContext toRiskContext(UserEvent event);

    public abstract RiskContext toRiskContext(AuthEvent event);

    public abstract RiskContext toRiskContext(DeviceEvent event);

    public abstract RiskContext toRiskContext(RiskSignalEvent event);

    @Mapping(target = "timsetamp", constant = "LocalDateTime.now()")
    public abstract RiskEvent toRiskEvent(RiskContext context, RiskScore score);

    public abstract RiskAlertMessage toRiskAlertMessage(RiskContext context, RiskScore score);

    public abstract StepUpEvent toStepUpEvent(RiskContext context, RiskScore score);

    @AfterMapping
    public void setDeviceEventAttributes(@MappingTarget RiskContext context, DeviceEvent event) {
        context.put("os", event.os());
        context.put("trusted", event.trusted());
        context.put("browser", event.browser());
        context.put("eventType", RiskSignalType.NEW_DEVICE);
    }

    @AfterMapping
    public void setRiskSignalEventAttributes(@MappingTarget RiskContext context, RiskSignalEvent event) {
        context.put("payload", event.payload());
    }

    @Override
    public RiskContext visit(UserEvent event) {
        return toRiskContext(event);
    }

    @Override
    public RiskContext visit(AuthEvent event) {
        return toRiskContext(event);
    }

    @Override
    public RiskContext visit(DeviceEvent event) {
        return toRiskContext(event);
    }

    @Override
    public RiskContext visit(RiskSignalEvent event) {
        return toRiskContext(event);
    }

}
