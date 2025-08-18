package io.rong.models.agent;

import io.rong.util.GsonUtil;

import java.util.Map;

/**
 * @author RongCloud
 */
public class ChatModel {
    private String conversationId;
    private Boolean memory;
    private String agentId;
    private String query;
    private Map<String, String> variables;
    private String user;

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public Boolean getMemory() {
        return memory;
    }

    public void setMemory(Boolean memory) {
        this.memory = memory;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ChatModel.class);
    }

}
