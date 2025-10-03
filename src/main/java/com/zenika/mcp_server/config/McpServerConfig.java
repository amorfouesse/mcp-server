package com.zenika.mcp_server.config;

import com.zenika.mcp_server.SncfService;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class McpServerConfig {
    @Bean
    public List<ToolCallback> danTools(SncfService sncfService) {
        return List.of(ToolCallbacks.from(sncfService));
    }
}
