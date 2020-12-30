package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * 广播消息体
 */
public class BroadcastMessage extends MessageModel {

    /**
     * 可选参数，可选值: iOS, Android
     */
    public String os;

    /**
     * 1 表示为开启，0 表示为关闭，默认为 0（可选）
     */
    public String contentAvailable = "0";

    /**
     * 是否为静默消息，默认为 false，设为 true 时终端用户离线情况下不会收到通知提醒（可选）。暂不支持海外数据中心
     */
    public Boolean disablePush;

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

    public Boolean getDisablePush() {   return disablePush;  }

    public BroadcastMessage setDisablePush(Boolean disablePush) {
        this.disablePush = disablePush;
        return this;
    }

}
