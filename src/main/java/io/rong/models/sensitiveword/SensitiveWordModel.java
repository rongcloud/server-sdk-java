package io.rong.models.sensitiveword;

import io.rong.util.GsonUtil;

/**
 * Sensitive word and replacement word information
 */
public class SensitiveWordModel {
    /**
     * Sensitive word type
     */
    Integer type = 1;
    /**
     * Sensitive word
     */
    String keyword;
    /**
     * Replacement word
     */
    String replace;

    public SensitiveWordModel(Integer type, String keyword, String replace) {
        this.type = type;
        this.keyword = keyword;
        this.replace = replace;
    }

    public SensitiveWordModel() {
    }

    /**
     * Set type
     */
    public SensitiveWordModel setType(Integer type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     *
     * @return String
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * Get sensitive word
     *
     * @return String
     */
    public String getKeyword() {
        return this.keyword;
    }

    /**
     * @param keyword Set sensitive word
     *
     */
    public SensitiveWordModel setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    /**
     * Gets the replacement text
     *
     * @return String
     */
    public String getReplace() {
        return this.replace;
    }

    /**
     * Sets the replacement text
     *
     */
    public SensitiveWordModel setReplace(String replace) {
        this.replace = replace;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, SensitiveWordModel.class);
    }
}
