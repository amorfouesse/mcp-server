package com.zenika.mcp_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
        @Bean
        public RestClient restClient() {
            return RestClient.builder()
                    .baseUrl("\n" + "https://api.navitia.io/v1/coverage/sncf/")
                    .build();
        }
}
