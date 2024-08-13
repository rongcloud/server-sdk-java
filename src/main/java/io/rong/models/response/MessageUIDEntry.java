package io.rong.models.response;

/**
 * @author huhangtao
 * @date 2024/8/8  14:54
 */
public class MessageUIDEntry {
    /**
     * 发送单聊消息有值
     */
    private String userId;
    /**
     * 发送群/超级群消息有值
     */
    private String groupId;
    /**
     * 发送聊天室消息有值
     */
    private String chatroomId;
    /**
     * 消息 ID
     */
    private String messageUID;

    public String getUserId() {
        return userId;
    }

    public MessageUIDEntry setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public MessageUIDEntry setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getChatroomId() {
        return chatroomId;
    }

    public MessageUIDEntry setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
        return this;
    }

    public String getMessageUID() {
        return messageUID;
    }

    public MessageUIDEntry setMessageUID(String messageUID) {
        this.messageUID = messageUID;
        return this;
    }
}
