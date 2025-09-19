package io.rong.models.conversation;

import java.util.List;

/**
 * @author RongCloud
 */
public class UserConversationTagModel {

    private String userId;

    private List<ConversationTagModel> tags;


    public UserConversationTagModel() {
    }

    public UserConversationTagModel(String userId, List<ConversationTagModel> tags) {
        this.userId = userId;
        this.tags = tags;
    }

    public String getUserId() {
        return userId;
    }

    public UserConversationTagModel setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public List<ConversationTagModel> getTags() {
        return tags;
    }

    public UserConversationTagModel setTags(List<ConversationTagModel> tags) {
        this.tags = tags;
        return this;
    }
}
