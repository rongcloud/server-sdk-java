package io.rong.models.agent;

import io.rong.util.GsonUtil;

import java.util.Map;

/**
 * @author RongCloud
 */
public class Prompt {
    private Map<String, String> variables;
    private String id;
    private String instructions;
    private String openingStatement;

    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getOpeningStatement() {
        return openingStatement;
    }

    public void setOpeningStatement(String openingStatement) {
        this.openingStatement = openingStatement;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, Prompt.class);
    }
}
