package io.rong.models.conversation;

/**
 * @author RongCloud
 */
public class ConversationTagModel {

    /**
     * Conversation tag ID; length must not exceed 10
     */
    private String tagId;

    /**
     * Conversation tag name; length must not exceed 15
     */
    private String tagName;

    private Long createTime;

    public ConversationTagModel() {
    }

    public ConversationTagModel(String tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public ConversationTagModel(String tagId, String tagName, Long createTime) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.createTime = createTime;
    }

    public String getTagId() {
        return tagId;
    }

    public ConversationTagModel setTagId(String tagId) {
        this.tagId = tagId;
        return this;
    }

    public String getTagName() {
        return tagName;
    }

    public ConversationTagModel setTagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public ConversationTagModel setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }
}
