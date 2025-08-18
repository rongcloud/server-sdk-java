package io.rong.models.response;


import io.rong.models.agent.AgentConfig;
import io.rong.util.GsonUtil;


/**
 * @author RongCloud
 */
public class GetAIAgentResult extends ResponseResult {

    private String agentId;
    private String name;
    private String description;
    private String type;
    private String status;
    private AgentConfig agentConfig;
    private Long createdAt;
    private Long updatedAt;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AgentConfig getAgentConfig() {
        return agentConfig;
    }

    public void setAgentConfig(AgentConfig agentConfig) {
        this.agentConfig = agentConfig;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public GetAIAgentResult(Integer code, String msg) {
        super(code, msg);
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, GetAIAgentResult.class);
    }
}
