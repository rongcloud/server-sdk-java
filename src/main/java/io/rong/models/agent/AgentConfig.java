package io.rong.models.agent;

import io.rong.util.GsonUtil;


/**
 * @author RongCloud
 */
public class AgentConfig {

    private Model model;
    private Prompt prompt;
    private Memory memory;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Prompt getPrompt() {
        return prompt;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, AgentConfig.class);
    }

}
