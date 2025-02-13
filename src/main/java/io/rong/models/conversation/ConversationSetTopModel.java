package io.rong.models.conversation;

/**
 * @author RongCloud
 */
public class ConversationSetTopModel {
    private String userId;
    private Integer conversationType;
    private String targetId;
    private Boolean setTop;

    public ConversationSetTopModel(String userId, Integer conversationType, String targetId, Boolean setTop) {
        this.userId = userId;
        this.conversationType = conversationType;
        this.targetId = targetId;
        this.setTop = setTop;
    }

    public ConversationSetTopModel() {
    }

    public String getUserId() {
        return userId;
    }

    public ConversationSetTopModel setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Integer getConversationType() {
        return conversationType;
    }

    public ConversationSetTopModel setConversationType(Integer conversationType) {
        this.conversationType = conversationType;
        return this;
    }

    public String getTargetId() {
        return targetId;
    }

    public ConversationSetTopModel setTargetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public Boolean getSetTop() {
        return setTop;
    }

    public ConversationSetTopModel setSetTop(Boolean setTop) {
        this.setTop = setTop;
        return this;
    }
}
