package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * Broadcast message body
 */
public class BroadcastMessage extends MessageModel {

    /**
     * Optional parameter, options: iOS, Android
     */
    public String os;

    /**
     * 1 indicates enabled, 0 indicates disabled, default is 0 (optional)
     */
    public String contentAvailable = "0";

    /**
     * Whether it is a silent message, default is false. When set to true, offline users will not receive notification alerts (optional). Not supported in global data centers.
     */
//    public Boolean disablePush;

    public BroadcastMessage() {
    }

    public BroadcastMessage(String senderUserId, String[] targetId, String objectName, BaseMessage content,
                            String pushContent, String pushData, String os) {
        super(senderUserId, targetId, objectName, content, pushContent, pushData, null);
        this.os = os;
    }

    public BroadcastMessage(String senderUserId, String[] targetId, String objectName, BaseMessage content,
                            String pushContent, String pushData, String os, String pushExt) {
        super(senderUserId, targetId, objectName, content, pushContent, pushData, pushExt);
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

    @Override
    public BroadcastMessage setPushExt(String pushExt) {
        super.setPushExt(pushExt);
        return this;
    }

    @Override
    public BroadcastMessage setPushExt(PushExt pe) {
        super.setPushExt(pe);
        return this;
    }

    public String getOs() {
        return this.os;
    }

    public BroadcastMessage setOs(String os) {
        this.os = os;
        return this;
    }

    public String getContentAvailable() {
        return contentAvailable;
    }

    public BroadcastMessage setContentAvailable(String contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }

    @Override
    public BroadcastMessage setMsgRandom(Long msgRandom) {
        super.setMsgRandom(msgRandom);
        return this;
    }

    //    public Boolean getDisablePush() {   return disablePush;  }
//
//    public BroadcastMessage setDisablePush(Boolean disablePush) {
//        this.disablePush = disablePush;
//        return this;
//    }

}
