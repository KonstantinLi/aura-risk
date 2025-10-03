package com.kpi.fict.aura.risk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;

import java.net.URI;

@Configuration
@RequiredArgsConstructor
public class VaultConfiguration {

    private final RiskProperties properties;

    @Bean
    public VaultTemplate vaultTemplate() {
        VaultEndpoint endpoint = VaultEndpoint.from(URI.create(properties.getVault().getUri()));
        return new VaultTemplate(endpoint, new TokenAuthentication(properties.getVault().getToken()));
    }

}