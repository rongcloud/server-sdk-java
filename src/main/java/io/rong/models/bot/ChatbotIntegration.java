package io.rong.models.bot;

import io.rong.models.agent.AgentModel;
import io.rong.util.GsonUtil;

import java.util.List;

public class ChatbotIntegration {
    private Boolean enabled;
    private String type;
    private String callbackUrl;
    private List<String> objectNames;
    private List<String> events;
    private Boolean stream;
    private Boolean handleResponse;
    protected ChatbotAuthInfo auth;
    private AgentModel agent;
    private Boolean waitForInputCompletion;
    private Boolean gentleResponse;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public List<String> getObjectNames() {
        return objectNames;
    }

    public void setObjectNames(List<String> objectNames) {
        this.objectNames = objectNames;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public Boolean getStream() {
        return stream;
    }

    public void setStream(Boolean stream) {
        this.stream = stream;
    }

    public Boolean getHandleResponse() {
        return handleResponse;
    }

    public void setHandleResponse(Boolean handleResponse) {
        this.handleResponse = handleResponse;
    }

    public ChatbotAuthInfo getAuth() {
        return auth;
    }

    public void setAuth(ChatbotAuthInfo auth) {
        this.auth = auth;
    }

    public AgentModel getAgent() {
        return agent;
    }

    public void setAgent(AgentModel agent) {
        this.agent = agent;
    }

    public Boolean getWaitForInputCompletion() {
        return waitForInputCompletion;
    }

    public void setWaitForInputCompletion(Boolean waitForInputCompletion) {
        this.waitForInputCompletion = waitForInputCompletion;
    }

    public Boolean getGentleResponse() {
        return gentleResponse;
    }

    public void setGentleResponse(Boolean gentleResponse) {
        this.gentleResponse = gentleResponse;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ChatbotIntegration.class);
    }
}
