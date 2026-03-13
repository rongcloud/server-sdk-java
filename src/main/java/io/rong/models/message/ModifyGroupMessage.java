package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * Group chat message modification request body.
 */
public class ModifyGroupMessage {

    /**
     * The sender user ID of the original message.
     */
    private String senderId;

    /**
     * The group ID of the original message.
     */
    private String groupId;

    /**
     * The unique identifier of the original message.
     */
    private String msgUID;

    /**
     * The modified message content.
     */
    private BaseMessage content;

    /**
     * Optional message type. It is mainly used for custom message types.
     */
    private String objectName;

    public ModifyGroupMessage() {
    }

    public ModifyGroupMessage(String senderId, String groupId, String msgUID, BaseMessage content) {
        this.senderId = senderId;
        this.groupId = groupId;
        this.msgUID = msgUID;
        this.content = content;
    }

    public String getSenderId() {
        return senderId;
    }

    public ModifyGroupMessage setSenderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public ModifyGroupMessage setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getMsgUID() {
        return msgUID;
    }

    public ModifyGroupMessage setMsgUID(String msgUID) {
        this.msgUID = msgUID;
        return this;
    }

    public BaseMessage getContent() {
        return content;
    }

    public ModifyGroupMessage setContent(BaseMessage content) {
        this.content = content;
        return this;
    }

    public String getObjectName() {
        return objectName;
    }

    public ModifyGroupMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }
}
