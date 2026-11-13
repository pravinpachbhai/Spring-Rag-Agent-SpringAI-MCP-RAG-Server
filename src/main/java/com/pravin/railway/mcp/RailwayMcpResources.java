package com.pravin.railway.mcp;

import com.pravin.railway.rag.PolicyRagService;
import org.springframework.ai.mcp.annotation.McpResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RailwayMcpResources {

    private final PolicyRagService rag;

    public RailwayMcpResources(PolicyRagService rag) {
        this.rag = rag;
    }

    @McpResource(
            uri = "railway://policies/notification",
            name = "Railway Notification Policy",
            description = "Provides railway notification policy information",
            mimeType = "text/plain"
    )
    public String getNotificationPolicy() {
        List<Map<String, Object>> results =
                rag.search("Railway Notification Policy", 4);

        if (results == null || results.isEmpty()) {
            return "No railway notification policy was found.";
        }

        return results.stream()
                .map(row -> String.valueOf(row.get("content")))
                .collect(Collectors.joining("\n\n---\n\n"));
    }
}