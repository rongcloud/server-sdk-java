package io.rong.models.conversation;

/**
 * @author RongCloud
 */
public class ConversationModel {
    public String type;
    public String userId;
    public String targetId;

    public ConversationModel() {
    }

    /**
     * 构造函数。
     *
     * @param type:会话类型。
     * @param userId:设置消息免打扰的用户 Id
     * @param targetId:目标Id
     *
     **/
    public ConversationModel(String type, String userId, String targetId) {
        this.type = type;
        this.userId = userId;
        this.targetId = targetId;
    }

    public String getType() {
        return this.type;
    }

    public ConversationModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getUserId() {
        return this.userId;
    }

    public ConversationModel setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public ConversationModel setTargetId(String targetId) {
        this.targetId = targetId;
        return this;
    }
}
