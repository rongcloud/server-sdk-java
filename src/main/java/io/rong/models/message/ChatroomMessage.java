package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * Chatroom message body
 * @author RongCloud
 */
public class ChatroomMessage extends MessageModel {

    public Integer isIncludeSender;
    public Integer isPersisted;
    /**
     * 0: Default level, maintains the original message level for sending.
     * 1: High-priority message, requires the "Chatroom User & Message allowlist" feature to be enabled. If not enabled, a status code will be returned. When the message uplink limit is triggered, this message will not be prioritized for discarding.
     * 2: High-level message, normal chatroom messages are high-level messages. If the message type is a high-priority message, you can downgrade a specific message to a high-level message.
     * 3: Low-level message, requires the "Chatroom Message Level Setting" feature to be enabled. If not enabled, a status code will be returned. When the message uplink limit is triggered, this message will be prioritized for discarding.
     */
    public Integer priority = null;
    public ChatroomMessage() {

    }

    public ChatroomMessage(String senderUserId, String[] targetId, String objectName, BaseMessage content) {
        super(senderUserId, targetId, objectName, content, null, null);
    }

   @Override
    public ChatroomMessage setSenderId(String senderId) {
        super.setSenderId(senderId);
        return this;
    }
    /**
     * Get the target chatroom ID
     *
     * @return String
     */
    @Override
    public String[] getTargetId() {
        return super.getTargetId();
    }
    /**
     * Set the target chatroom ID
     *
     * @return String
     */
    @Override
    public ChatroomMessage setTargetId(String[] targetId) {
        super.setTargetId(targetId);
        return this;
    }
    @Override
    public ChatroomMessage setObjectName(String objectName) {
        super.setObjectName(objectName);
        return this;
    }

    public Integer getIsPersisted() {
        return isPersisted;
    }

    public ChatroomMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    public Integer getIsIncludeSender() {
        return this.isIncludeSender;
    }

    public ChatroomMessage setIsIncludeSender(Integer isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public ChatroomMessage setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public ChatroomMessage setContent(BaseMessage content) {
        super.setContent(content);
        return this;
    }

    @Override
    public ChatroomMessage setMsgRandom(Long msgRandom) {
        super.setMsgRandom(msgRandom);
        return this;
    }
}
