# Enterprise Railway AI 

- PostgreSQL-backed trains, incidents, workflow, approvals, notifications and audit events
- Spring AI + Ollama for chat/tool calling
- PostgreSQL/pgvector RAG for policy documents
- MCP server tools for read-only railway lookups and policy search
- Multi-step notification workflow with approval before dispatch
- Mock notification delivery (does not send real email/SMS)

## Prerequisites
- Java 25
- Maven 3.9+
- Docker Desktop

## Start dependencies
```bash
docker compose up -d
docker exec -it railway-ollama ollama pull llama3.2:3b
docker exec -it railway-ollama ollama pull nomic-embed-text
```

## Run application
```bash
mvn spring-boot:run
```
App: http://localhost:8080  
Health: http://localhost:8080/actuator/health  
MCP endpoint is served by Spring AI MCP server configuration (Streamable HTTP/SSE transport according to the configured starter).

