package com.zenika.mcp_server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Value("${sncf.api.token}")
    private String apiToken;

    @Value("${sncf.api.base-url}")
    private String apiBaseUrl;

    @Bean
    public RestClient sncfRestClient() {
        return RestClient.builder()
                .baseUrl(apiBaseUrl)
                .defaultHeader("Authorization", apiToken)
                .build();
    }
}