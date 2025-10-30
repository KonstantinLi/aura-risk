package com.kpi.fict.aura.risk.observability;

import com.kpi.fict.aura.risk.dto.RiskScore;
import com.kpi.fict.aura.risk.model.RiskLevel;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class PipelineMetricsAspect {

    private final RiskMetrics riskMetrics;

    @Around("execution(* com.kpi.fict.aura.risk.pipeline.RiskPipeline.run(..))")
    public Object aroundRun(ProceedingJoinPoint joinPoint) throws Throwable {
        riskMetrics.incrementEvent();
        Object result = joinPoint.proceed();
        if (result instanceof RiskScore) {
            RiskScore score = (RiskScore) result;
            if (score.level() != RiskLevel.LOW) {
                riskMetrics.incrementStepUp();
            }
            if (score.level() == RiskLevel.HIGH) {
                riskMetrics.incrementAlert();
            }
        }
        return result;
    }

}
