package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * One-to-one chat message modification request body.
 */
public class ModifyPrivateMessage {

    /**
     * The sender user ID of the original message.
     */
    private String senderId;

    /**
     * The receiver user ID of the original message.
     */
    private String targetId;

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

    public ModifyPrivateMessage() {
    }

    public ModifyPrivateMessage(String senderId, String targetId, String msgUID, BaseMessage content) {
        this.senderId = senderId;
        this.targetId = targetId;
        this.msgUID = msgUID;
        this.content = content;
    }

    public String getSenderId() {
        return senderId;
    }

    public ModifyPrivateMessage setSenderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    public String getTargetId() {
        return targetId;
    }

    public ModifyPrivateMessage setTargetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public String getMsgUID() {
        return msgUID;
    }

    public ModifyPrivateMessage setMsgUID(String msgUID) {
        this.msgUID = msgUID;
        return this;
    }

    public BaseMessage getContent() {
        return content;
    }

    public ModifyPrivateMessage setContent(BaseMessage content) {
        this.content = content;
        return this;
    }

    public String getObjectName() {
        return objectName;
    }

    public ModifyPrivateMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }
}
