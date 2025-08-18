package io.rong.models.agent;

import io.rong.util.GsonUtil;


/**
 * @author RongCloud
 */
public class Memory {
    private String strategy;
    private Integer maxMessages;
    private Integer maxTokens;

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public Integer getMaxMessages() {
        return maxMessages;
    }

    public void setMaxMessages(Integer maxMessages) {
        this.maxMessages = maxMessages;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, Memory.class);
    }
}
