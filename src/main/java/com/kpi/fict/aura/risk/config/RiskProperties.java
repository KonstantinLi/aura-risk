package com.kpi.fict.aura.risk.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "kafka")
public class RiskProperties {

    private final Kafka kafka = new Kafka();
    private final Redis redis = new Redis();
    private final Vault vault = new Vault();
    private final Async async = new Async();
    private final Database database = new Database();

    @Getter
    @Setter
    public static class Kafka {
        private String groupId = "risk-engine";
        private String bootstrapServers = "localhost:9092";
    }

    @Getter
    @Setter
    public static class Redis {
        private int database = 0;
        private int port = 6379;
        private String host = "localhost";
    }

    @Getter
    @Setter
    public static class Database {
        private String url;
        private String username;
        private String password;
    }

    @Getter
    @Setter
    public static class Vault {
        private String token;
        private String uri = "http://localhost:8200";
    }

    @Getter
    @Setter
    public static class Async {
        private int corePoolSize = 4;
        private int maxPoolSize = 16;
        private int queueCapacity = 500;
    }

}