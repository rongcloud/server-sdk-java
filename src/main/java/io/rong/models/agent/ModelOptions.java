package io.rong.models.agent;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * @author RongCloud
 */
public class ModelOptions {

    private Float temperature;
    private Float topP;
    private Integer maxTokens;
    private Float frequencyPenalty;
    private Float presencePenalty;
    private List<String> stop;

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getTopP() {
        return topP;
    }

    public void setTopP(Float topP) {
        this.topP = topP;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public Float getFrequencyPenalty() {
        return frequencyPenalty;
    }

    public void setFrequencyPenalty(Float frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
    }

    public Float getPresencePenalty() {
        return presencePenalty;
    }

    public void setPresencePenalty(Float presencePenalty) {
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
