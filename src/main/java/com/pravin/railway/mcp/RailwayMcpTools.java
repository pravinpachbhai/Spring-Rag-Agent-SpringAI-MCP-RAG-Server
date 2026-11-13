package com.pravin.railway.mcp;

import com.pravin.railway.domain.RailwayService;
import com.pravin.railway.rag.PolicyRagService;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RailwayMcpTools {
    private final RailwayService railway;
    private final PolicyRagService rag;

    public RailwayMcpTools(RailwayService railway, PolicyRagService rag) {
        this.railway = railway;
        this.rag = rag;
    }

    @McpTool(name ="railway_get_train_status", description = "Get the stored operational status and location of a railway train. Read-only.")
    public Map<String, Object> getTrainStatus(
            @McpToolParam(description = "Train identifier, for example CSXT-7821") String trainId) {
        return railway.getTrain(trainId);
    }

    @McpTool(name ="railway_open_incidents", description = "List open railway incidents from the operational database. Read-only.")
    public List<Map<String, Object>> listOpenIncidents() {
        return railway.getOpenIncidents();
    }

    @McpTool(name ="railway_search_notification_policy", description = "Search ingested railway notification policy documents. Read-only.")
    public List<Map<String, Object>> searchNotificationPolicy(
            @McpToolParam(description = "Natural-language query about a railway policy") String query) {
        return rag.search(query, 4);
    }
}
