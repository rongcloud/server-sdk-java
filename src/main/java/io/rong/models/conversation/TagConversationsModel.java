package io.rong.models.conversation;

import java.util.List;

/**
 * @author RongCloud
 */
public class TagConversationsModel {


    private String userId;

    private String tagId;


    public List<Conversation> conversations;

    public TagConversationsModel() {
    }

    public TagConversationsModel(String userId, String tagId, List<Conversation> conversations) {
        this.userId = userId;
        this.tagId = tagId;
        this.conversations = conversations;
    }

    public String getUserId() {
        return userId;
    }

    public TagConversationsModel setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getTagId() {
        return tagId;
    }

    public TagConversationsModel setTagId(String tagId) {
        this.tagId = tagId;
        return this;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public TagConversationsModel setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
        return this;
    }

    public static class Conversation{


        private String targetId;

        private Integer conversationType;

        public Conversation() {
        }

        public Conversation(String targetId, Integer conversationType) {
            this.targetId = targetId;
            this.conversationType = conversationType;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public Integer getConversationType() {
            return conversationType;
        }

        public void setConversationType(Integer conversationType) {
            this.conversationType = conversationType;
        }
    }


}
