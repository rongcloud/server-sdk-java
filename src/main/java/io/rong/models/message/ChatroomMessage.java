package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * 聊天室消息体
 * @author RongCloud
 */
public class ChatroomMessage extends MessageModel {

    public Integer isIncludeSender;
    public Integer isPersisted;
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

    @Override
    public ChatroomMessage setContent(BaseMessage content) {
        super.setContent(content);
        return this;
    }
}
