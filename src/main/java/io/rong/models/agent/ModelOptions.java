package io.rong.models.agent;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * @author RongCloud
 */
public class ModelOptions {

    private Integer temperature;
    private Integer topP;
    private Integer maxTokens;
    private Integer frequencyPenalty;
    private Integer presencePenalty;
    private List<String> stop;

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getTopP() {
        return topP;
    }

    public void setTopP(Integer topP) {
        this.topP = topP;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public Integer getFrequencyPenalty() {
        return frequencyPenalty;
    }

    public void setFrequencyPenalty(Integer frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
    }

    public Integer getPresencePenalty() {
        return presencePenalty;
    }

    public void setPresencePenalty(Integer presencePenalty) {
        this.presencePenalty = presencePenalty;
    }

    public List<String> getStop() {
        return stop;
    }

    public void setStop(List<String> stop) {
        this.stop = stop;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ModelOptions.class);
    }

}
