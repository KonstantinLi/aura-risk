package com.kpi.fict.aura.risk.observability;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class RiskMetrics {

    private final MeterRegistry registry;

    private Counter stepUpCounter;
    private Counter riskEventsCounter;
    private Counter riskAlertsCounter;

    private Timer pipelineTimer;
    private AtomicInteger activePipelines = new AtomicInteger(0);

    @PostConstruct
    public void init() {
        this.riskEventsCounter = Counter.builder("risk.events.total")
                .description("Total number of processed risk events")
                .register(registry);

        this.riskAlertsCounter = Counter.builder("risk.alerts.total")
                .description("Total number of published risk alerts")
                .register(registry);

        this.stepUpCounter = Counter.builder("risk.stepup.total")
                .description("Total number of step-up notifications triggered")
                .register(registry);

        this.pipelineTimer = Timer.builder("risk.pipeline.latency")
                .description("Latency of risk pipeline (enrich→evaluate→persist→publish)")
                .publishPercentileHistogram()
                .register(registry);

        Gauge.builder("risk.pipeline.active", activePipelines, AtomicInteger::get)
                .description("Number of active pipeline runs")
                .register(registry);
    }

    public <T> T recordPipeline(RunnableWithResult<T> runnable) {
        activePipelines.incrementAndGet();
        try {
            long start = System.nanoTime();
            T result = runnable.run();
            long duration = System.nanoTime() - start;
            pipelineTimer.record(duration, java.util.concurrent.TimeUnit.NANOSECONDS);
            return result;
        } finally {
            activePipelines.decrementAndGet();
        }
    }

    public void recordPipeline(Runnable runnable) {
        activePipelines.incrementAndGet();
        try {
            registry.timer("risk.pipeline.latency").record(runnable);
        } finally {
            activePipelines.decrementAndGet();
        }
    }

    public void incrementEvent() {
        riskEventsCounter.increment();
    }

    public void incrementAlert() {
        riskAlertsCounter.increment();
    }

    public void incrementStepUp() {
        stepUpCounter.increment();
    }

    @FunctionalInterface
    public interface RunnableWithResult<T> {
        T run();
    }

}