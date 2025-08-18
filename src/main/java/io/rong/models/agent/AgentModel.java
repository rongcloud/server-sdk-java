package io.rong.models.agent;

import io.rong.util.GsonUtil;


/**
 * @author RongCloud
 */
public class AgentModel {
    private String agentId;
    private String name;
    private String description;
    private String type;
    private String status;
    private AgentConfig agentConfig;

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

    @Override
    public String toString() {
        return GsonUtil.toJson(this, AgentModel.class);
    }

}
