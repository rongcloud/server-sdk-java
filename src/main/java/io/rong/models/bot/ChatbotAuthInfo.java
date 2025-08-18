package io.rong.models.bot;

import io.rong.util.GsonUtil;


/**
 * @author RongCloud
 */
public class ChatbotAuthInfo {
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ChatbotAuthInfo.class);
    }
}
