package com.kpi.fict.aura.risk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@RequiredArgsConstructor
public class AsyncConfiguration {

    private final RiskProperties properties;

    @Bean(name = "riskExecutor")
    public Executor riskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(properties.getAsync().getMaxPoolSize());
        executor.setCorePoolSize(properties.getAsync().getCorePoolSize());
        executor.setQueueCapacity(properties.getAsync().getQueueCapacity());
        executor.setThreadNamePrefix("risk-exec-");
        executor.initialize();
        return executor;
    }

}