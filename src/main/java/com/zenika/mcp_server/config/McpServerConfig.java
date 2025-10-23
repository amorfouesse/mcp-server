package com.zenika.mcp_server.config;

import com.zenika.mcp_server.service.SncfService;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class McpServerConfig {
    @Bean
    public ToolCallbackProvider weatherTools(SncfService sncfService) {
        return MethodToolCallbackProvider.builder().toolObjects(sncfService).build();
    }
}
