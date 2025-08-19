package io.rong.models.bot;

import io.rong.util.GsonUtil;

import java.util.List;
import java.util.Map;

/**
 * @author RongCloud
 */
public class ChatbotInfoModel {
    private String userId;
    private String name;
    private String type;
    private String profileUrl;
    private Map<String, String> metadata;
    private List<ChatbotIntegration> integrations;
    private Long createdAt;
    private Long updatedAt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public List<ChatbotIntegration> getIntegrations() {
        return integrations;
    }

    public void setIntegrations(List<ChatbotIntegration> integrations) {
        this.integrations = integrations;
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

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ChatbotInfoModel.class);
    }

}
