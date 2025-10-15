package com.kpi.fict.aura.risk.mapper;

import com.kpi.fict.aura.risk.ingestion.context.RiskContext;
import com.kpi.fict.aura.risk.dto.AuthEvent;
import com.kpi.fict.aura.risk.dto.DeviceEvent;
import com.kpi.fict.aura.risk.dto.RiskSignalEvent;
import com.kpi.fict.aura.risk.dto.UserEvent;

public interface EventVisitor {

    RiskContext visit(UserEvent event);

    RiskContext visit(AuthEvent event);

    RiskContext visit(DeviceEvent event);

    RiskContext visit(RiskSignalEvent event);

}