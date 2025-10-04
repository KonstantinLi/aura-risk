package com.kpi.fict.aura.risk.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {AuraRiskConfiguration.RISK_BASE_PACKAGE})
@ComponentScan(basePackages = {AuraRiskConfiguration.RISK_BASE_PACKAGE})
@EnableJpaRepositories(basePackages = {AuraRiskConfiguration.RISK_BASE_PACKAGE})
@ConfigurationPropertiesScan(basePackages = {AuraRiskConfiguration.RISK_BASE_PACKAGE})
@Import({
        RedisConfiguration.class, VaultConfiguration.class,
        AsyncConfiguration.class, DatabaseConfiguration.class,
        WebsocketConfiguration.class, MetricsConfiguration.class,
        KafkaConfiguration.class, ObjectMapperConfiguration.class})
public class AuraRiskConfiguration {

    public static final String RISK_BASE_PACKAGE = "com.kpi.fict.aura.risk";

}