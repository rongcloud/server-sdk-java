package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

public class ConversationNotificationResult extends Result {

    // Indicates the status of the Do Not Disturb setting for messages, where 0 means disabled and 1 means enabled.
    Integer isMuted;

    public ConversationNotificationResult(Integer code, Integer isMuted, String errorMessage) {
        super(code,errorMessage);
        this.code = code;
        this.isMuted = isMuted;
        this.errorMessage = errorMessage;
    }

    /**
     * Sets the code.
     *
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Gets the code.
     *
     * @return Integer
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Gets the Muted status.
     *
     * @return String
     */
    public Integer getIsMuted() {
        return this.isMuted;
    }
    /**
     * Sets the Muted status.
     *
     */
    public void setIsMuted(Integer isMuted) {
        this.isMuted = isMuted;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ConversationNotificationResult.class);
    }
}
