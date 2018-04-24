package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * 广播消息体
 * @author hc
 */
public class BroadcastMessage extends MessageModel {

    public String os;

    public BroadcastMessage() {
    }

    public BroadcastMessage(String senderUserId, String[] targetId, String objectName, BaseMessage content, String pushContent, String pushData,
                            String os) {
        super(senderUserId, targetId, objectName, content, pushContent, pushData);
        this.os = os;
    }
    @Override
    public BroadcastMessage setSenderId(String senderId) {
        super.setSenderId(senderId);
        return this;
    }

    @Override
    public BroadcastMessage setObjectName(String objectName) {
        super.setObjectName(objectName);
        return this;
    }
    @Override
    public BroadcastMessage setContent(BaseMessage content) {
        super.setContent(content);
        return this;
    }
     @Override
    public BroadcastMessage setPushContent(String pushContent) {
        super.setPushContent(pushContent);
        return this;
    }
    @Override
    public BroadcastMessage setPushData(String pushData) {
        super.setPushData(pushData);
        return this;
    }

    public String getOs() {
        return this.os;
    }

    public BroadcastMessage setOs(String os) {
        this.os = os;
        return this;
    }
}
