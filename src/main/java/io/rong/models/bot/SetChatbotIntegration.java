package io.rong.models.bot;

import io.rong.util.GsonUtil;

public class SetChatbotIntegration extends ChatbotIntegration {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, SetChatbotIntegration.class);
    }
}
