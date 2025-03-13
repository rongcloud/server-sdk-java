package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * System message body
 *
 * @author hc
 */
public class SystemMessage extends MessageModel {

    /**
     * When a new custom message is available in the current version but not in the old version, 
     * specifies whether the old version client stores the message after receiving it. 
     * 0 indicates not to store, 1 indicates to store. Default is 1 (store message). (Optional)
     */
    private Integer isPersisted;
    /**
     * When a new custom message is available in the current version but not in the old version, 
     * specifies whether the old version client counts the message as unread after receiving it. 
     * 0 indicates not to count, 1 indicates to count. Default is 1 (count message, increment unread count by 1). (Optional)
     */
    private Integer isCounted;
    /**
     * For iOS platform, specifies whether to enable silent push when the SDK is in the background suspended state. 
     * Silent push is a push method introduced after iOS7, allowing the app to run a piece of code in the background 
     * immediately after receiving the notification. 1 indicates enabled, 0 indicates disabled. Default is 0. (Optional)
     */
    private Integer contentAvailable;

    /**
     * Specifies whether the message is silent. Default is false. When set to true, terminal users will not receive 
     * notification reminders when offline. (Optional). Not supported in global data center.
     */
    public Boolean disablePush;

    public SystemMessage() {
    }

    public SystemMessage(String senderUserId, String[] targetId, String objectName, BaseMessage content,
                         String pushContent, String pushData, Integer isPersisted, Integer isCounted, Integer contentAvailable) {
        super(senderUserId, targetId, objectName, content, pushContent, pushData);
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.contentAvailable = contentAvailable;
    }

    public SystemMessage(String senderUserId, String[] targetId, String objectName, BaseMessage content,
                         String pushContent, String pushData, String pushExt, Integer isPersisted, Integer isCounted,
                         Integer contentAvailable) {
        super(senderUserId, targetId, objectName, content, pushContent, pushData, pushExt);
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.contentAvailable = contentAvailable;
    }


    @Override
    public SystemMessage setSenderId(String senderId) {
        super.setSenderId(senderId);
        return this;
    }

    /**
     * Get the chatroom ID
     *
     * @return String
     */
    @Override
    public String[] getTargetId() {
        return super.getTargetId();
    }

    /**
     * User IDs to receive the message. Multiple IDs can be provided to send system messages to multiple users, with a maximum of 100 users.
     */
    @Override
    public SystemMessage setTargetId(String[] targetId) {
        super.setTargetId(targetId);
        return this;
    }

    @Override
    public SystemMessage setContent(BaseMessage content) {
        super.setContent(content);
        return this;

    }

    @Override
    public SystemMessage setPushContent(String pushContent) {
        super.setPushContent(pushContent);
        return this;
    }

    @Override
    public SystemMessage setPushData(String pushData) {
        super.setPushData(pushData);
        return this;
    }

    @Override
    public SystemMessage setPushExt(String pushExt) {
        super.setPushExt(pushExt);
        return this;
    }

    @Override
    public SystemMessage setPushExt(PushExt pe) {
        super.setPushExt(pe);
        return this;
    }

    public Integer getIsPersisted() {
        return this.isPersisted;
    }

    public SystemMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    public Integer getIsCounted() {
        return this.isCounted;
    }

    public SystemMessage setIsCounted(Integer isCounted) {
        this.isCounted = isCounted;
        return this;
    }

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public SystemMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }

    @Override
    public SystemMessage setObjectName(String objectName) {
        super.setObjectName(objectName);
        return this;
    }

    public Boolean getDisablePush() {   return disablePush;  }

    public SystemMessage setDisablePush(Boolean disablePush) {
        this.disablePush = disablePush;
        return this;
    }

    @Override
    public SystemMessage setMsgRandom(Long msgRandom) {
        super.setMsgRandom(msgRandom);
        return this;
    }
}
