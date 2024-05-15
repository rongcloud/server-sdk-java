package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * 聊天室消息体
 * @author RongCloud
 */
public class ChatroomMessage extends MessageModel {

    public Integer isIncludeSender;
    public Integer isPersisted;
    /**
     * 0:默认状态,保持原消息的消息级别进行发送。
     * 1:高保障消息,该设置需要开启“聊天室用户&消息白名单”功能后才能使用，否则返回状态码，触发消息上行上限时，不会被优先抛弃
     * 2:高级别消息,正常聊天室发送的消息都为高级别消息，如消息类型为高保障消息时，在发送此类消息时，可针对此类消息中的某一条消息进行降级设置为高级别消息。
     * 3:低级别消息,该设置需要开启“聊天室消息级别设置”功能后才能使用，否则返回状态码，触发消息上行上限时，会被优先抛弃
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
     * 获取接受聊天室Id
     *
     * @return String
     */
    @Override
    public String[] getTargetId() {
        return super.getTargetId();
    }
    /**
     * 设置接受聊天室Id
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
