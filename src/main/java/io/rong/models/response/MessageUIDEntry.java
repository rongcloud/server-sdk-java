package io.rong.models.response;

/**
 * @author huhangtao
 * @date 2024/8/8  14:54
 */
public class MessageUIDEntry {
    /**
     * Indicates the value when sending a one-to-one chat message.
     */
    private String userId;
    /**
     * Indicates the value when sending a group or ultra group message.
     */
    private String groupId;
    /**
     * Indicates the value when sending a chatroom message.
     */
    private String chatroomId;
    /**
     * Specifies the Message ID.
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
