package io.rong.models.message;

import io.rong.messages.BaseMessage;

import java.util.HashMap;

/**
 * Ultra group message body
 *
 */
public class UltraGroupMessage extends MessageModel {

    /**
     * Indicates whether this message is stored in the RongCloud server's history messages. The client determines whether to store it based on the ISPERSISTED flag registered with the message. For custom messages, if the message is not registered on older clients, this property determines whether it is stored locally, although it cannot be parsed and displayed. 0 means not stored, 1 means stored. Default is 1. This property does not affect offline message functionality; messages are always stored as offline messages when the user is offline.
     */
    public Integer isPersisted;

    /**
     * iOS silent push notification. 0 means off, 1 means on.
     **/
    public Integer contentAvailable;

    /**
     * Indicates whether this is a mention message. 0 means a regular message, 1 means a mention message. Default is 0. When set to 1, the content parameter must include mentionedInfo with the details of the mention. When set to 0, mentionedInfo is not required.
     */
    public Integer isMentioned;

    /**
     * Channel ID. When sending a message, the system validates the channel ID under the group ID. If the channel ID does not exist under the group ID, the message sending process is terminated. Parameter validation: a-zA-Z0-9, no other characters allowed, including underscores. Maximum length is 20 characters.
     */
    public String busChannel;

    /**
     * Indicates whether this is an extensible message. Default is false. When set to true, the terminal can set extension information for this message upon receiving it (optional). Not supported in global data centers.
     */
    public Boolean expansion;

    /**
     * Extended message content. Takes effect when expansion is true.
     */
    public HashMap<String, String> extraContent;

    /**
     * Indicates whether to count this message in the unread message count when the user is offline. 0 means not counted, 1 means counted. Default is 1.
     */
    public Integer isCounted;

    public UltraGroupMessage() {
    }

    /**
     * @param senderId: The sender's user ID. (Required)
     * @param targetId: The target group ID. Multiple IDs can be provided to send messages to multiple groups, with a maximum of 3 groups. (Required)
     * @param content: The message content to be sent. Refer to the RongCloud message type table for examples. If the objectName is a custom message type, this parameter can be customized. (Required)
     * @param pushContent: Defines the push notification content to be displayed. If the objectName is a built-in RongCloud message type, the user will always receive a push notification. If it's a custom message, pushContent defines the push notification content. If not provided, the user will not receive a push notification. (Optional)
     * @param pushData: For iOS platforms, this is the additional data attached to the push notification payload. For Android clients, the corresponding field name is pushData. (Optional)
     * @param isPersisted: Determines whether the message is stored when a new custom message type is introduced in the current version but not supported in older versions. 0 indicates no storage, 1 indicates storage. Default is 1. (Optional)
     */
    public UltraGroupMessage(String senderId, String[] targetId, String objectName, BaseMessage content, String pushContent,
                             String pushData, Integer isPersisted, Integer contentAvailable) {
        super(senderId, targetId, objectName, content, pushContent, pushData);
        this.isPersisted = isPersisted;
        //this.isCounted = isCounted;
        this.contentAvailable = contentAvailable;
    }
    public UltraGroupMessage(String senderId, String[] targetId, String objectName, BaseMessage content, String pushContent,
                             String pushData, Integer isPersisted, Integer contentAvailable, Integer isMentioned) {
        super(senderId, targetId, objectName, content, pushContent, pushData);
        this.isPersisted = isPersisted;
        //this.isCounted = isCounted;
        this.contentAvailable = contentAvailable;
        this.isMentioned = isMentioned;
    }

    public UltraGroupMessage(String senderId, String[] targetId, String objectName, BaseMessage content, String pushContent,
                             String pushData, String pushExt, Integer isPersisted, Integer contentAvailable) {
        super(senderId, targetId, objectName, content, pushContent, pushData, pushExt);
        this.isPersisted = isPersisted;
        this.contentAvailable = contentAvailable;
    }

    public UltraGroupMessage(String senderId, String[] targetId, String objectName, BaseMessage content, String pushContent,
                             String pushData, Integer isPersisted, Integer contentAvailable,
                             String busChannel) {
        super(senderId, targetId, objectName, content, pushContent, pushData);
        this.isPersisted = isPersisted;
        this.contentAvailable = contentAvailable;
        this.busChannel = busChannel;
    }

    public UltraGroupMessage(String senderId, String[] targetId, String objectName, BaseMessage content, String pushContent,
                             String pushData, String pushExt, Integer isPersisted,
                             Integer contentAvailable, String busChannel) {
        super(senderId, targetId, objectName, content, pushContent, pushData, pushExt);
        this.isPersisted = isPersisted;
        this.contentAvailable = contentAvailable;
        this.busChannel = busChannel;
    }

    public UltraGroupMessage(String senderId, String[] targetId, String objectName, BaseMessage content, String pushContent,
                             String pushData, String pushExt, Integer isPersisted,
                             Integer contentAvailable, String busChannel, Integer isMentioned) {
        super(senderId, targetId, objectName, content, pushContent, pushData, pushExt);
        this.isPersisted = isPersisted;
        this.contentAvailable = contentAvailable;
        this.busChannel = busChannel;
        this.isMentioned = isMentioned;
    }



    @Override
    public UltraGroupMessage setSenderId(String senderId) {
        super.setSenderId(senderId);
        return this;
    }

    /**
     * Get the target group ID
     *
     * @return String
     */
    @Override
    public String[] getTargetId() {
        return super.getTargetId();
    }

    /**
     * Set the target group ID
     *
     * @return String
     */
    @Override
    public UltraGroupMessage setTargetId(String[] targetId) {
        super.setTargetId(targetId);
        return this;
    }

    @Override
    public UltraGroupMessage setContent(BaseMessage content) {
        super.setContent(content);
        return this;
    }

    @Override
    public UltraGroupMessage setPushContent(String pushContent) {
        super.setPushContent(pushContent);
        return this;
    }

    @Override
    public UltraGroupMessage setPushData(String pushData) {
        super.setPushData(pushData);
        return this;
    }

    @Override
    public UltraGroupMessage setPushExt(String pushExt) {
        super.setPushExt(pushExt);
        return this;
    }

    @Override
    public UltraGroupMessage setPushExt(PushExt pe) {
        super.setPushExt(pe);
        return this;
    }

    public Integer getIsPersisted() {
        return this.isPersisted;
    }

    public UltraGroupMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public UltraGroupMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }

    @Override
    public UltraGroupMessage setObjectName(String objectName) {
        super.setObjectName(objectName);
        return this;
    }

    public String getBusChannel() {
        return busChannel;
    }

    public UltraGroupMessage setBusChannel(String busChannel) {
        this.busChannel = busChannel;
        return this;
    }

    public Integer getIsMentioned() {
        return isMentioned;
    }

    public UltraGroupMessage setIsMentioned(Integer isMentioned) {
        this.isMentioned = isMentioned;
        return this;
    }

    public Boolean getExpansion() {
        return expansion;
    }

    public UltraGroupMessage setExpansion(Boolean expansion) {
        this.expansion = expansion;
        return this;
    }

    public HashMap<String, String> getExtraContent() {
        return extraContent;
    }

    public UltraGroupMessage setExtraContent(HashMap<String, String> extraContent) {
        this.extraContent = extraContent;
        return this;
    }

    public Integer getIsCounted() {
        return isCounted;
    }

    public UltraGroupMessage setIsCounted(Integer isCounted) {
        this.isCounted = isCounted;
        return this;
    }

    @Override
    public UltraGroupMessage setMsgRandom(Long msgRandom) {
        super.setMsgRandom(msgRandom);
        return this;
    }
}
