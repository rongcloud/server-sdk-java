package io.rong.models.message;

import io.rong.messages.BaseMessage;

import java.util.HashMap;

/**
 * Group message body
 *
 * @author hc
 */
public class GroupMessage extends MessageModel {

    /**
     * Whether the sender receives this message, 0: No, 1: Yes, default: 0
     **/
    public Integer isIncludeSender;
    /**
     * For iOS platform, controls the display of unread message count in Push notifications. Only valid when toUserId is a single user ID. (Optional)
     */
    public Integer isPersisted;
    /**
     * When a new custom message is introduced in the current version and the older version does not support it, whether the older version client should count the message as unread.
     * 0: Do not count, 1: Count, default: 1 (Count), increments the unread message count by 1. (Optional)
     */
    //public Integer isCounted;

    /**
     * iOS silent push, 0: Disabled, 1: Enabled
     **/
    public Integer contentAvailable;
    /**
     * Targeted user IDs
     **/
    public String[] toUserId;

    /**
     * Whether the message is silent, default: false. When set to true, offline users will not receive notification alerts. (Optional). Not supported in global data center.
     */
    public Boolean disablePush;

    /**
     * Whether the message is extensible, default: false. When set to true, the client can set extended information for this message upon receipt. (Optional). Not supported in global data center.
     */
    public Boolean expansion;

    /**
     * Extended message content, effective when expansion is true.
     */
    public HashMap<String, String> extraContent;

    /**
     * Indicates whether it is a mention message. Defaults to 0 (non-mention message) if not provided.
     * To send a mention message, set this to 1 and include mention-related information (mentionedInfo) in the message content field (content).
     * For detailed information on the mentionedInfo structure, refer to the guide on how to send mention messages.
     */
    public Integer isMentioned;


    public GroupMessage() {
    }

    /**
     * @param senderId: The sender's user ID. (Required)
     * @param targetId: The target group ID(s). Up to 3 groups can be specified for sending messages to multiple groups. (Required)
     * @param content: The message content. Refer to the RongCloud message type table for examples. If objectName is a custom message type, this parameter can be customized. (Required)
     * @param pushContent: The push notification content displayed to users. If objectName is a built-in RongCloud message type, users will always receive a push notification. For custom messages, pushContent defines the push notification content. If not provided, users will not receive a push notification. (Optional)
     * @param pushData: Additional data attached to the push notification payload for iOS. Android clients will receive this data under the field name pushData. (Optional)
     * @param isPersisted: Determines whether older clients should store the message if they do not support the new custom message type. 0 means do not store, 1 means store. Defaults to 1. (Optional)
     * @param isCounted: Determines whether older clients should count the message as unread if they do not support the new custom message type. 0 means do not count, 1 means count. Defaults to 1, incrementing the unread message count by 1. (Optional)
     * @param isIncludeSender: Determines whether the sender should receive the message. 0 means do not receive, 1 means receive. Defaults to 0. (Optional)
     */
    public GroupMessage(String senderId, String[] targetId, String objectName, BaseMessage content, String pushContent,
                        String pushData, Integer isIncludeSender, Integer isPersisted, Integer isCounted, Integer contentAvailable) {
        super(senderId, targetId, objectName, content, pushContent, pushData);
        this.isIncludeSender = isIncludeSender;
        this.isPersisted = isPersisted;
        //this.isCounted = isCounted;
        this.contentAvailable = contentAvailable;
    }

    public GroupMessage(String senderId, String[] targetId, String objectName, BaseMessage content, String pushContent,
                        String pushData, String pushExt, Integer isIncludeSender, Integer isPersisted, Integer isCounted,
                        Integer contentAvailable) {
        super(senderId, targetId, objectName, content, pushContent, pushData, pushExt);
        this.isIncludeSender = isIncludeSender;
        this.isPersisted = isPersisted;
        //this.isCounted = isCounted;
        this.contentAvailable = contentAvailable;
    }

    /**
     * @param senderId: The sender's user ID. (Required)
     * @param targetId: The target group ID(s). Up to 3 groups can be specified for sending messages to multiple groups. (Required)
     * @param content: The message content. Refer to the RongCloud message type table for examples. If objectName is a custom message type, this parameter can be customized. (Required)
     * @param pushContent: The push notification content displayed to users. If objectName is a built-in RongCloud message type, users will always receive a push notification. For custom messages, pushContent defines the push notification content. If not provided, users will not receive a push notification. (Optional)
     * @param pushData: Additional data attached to the push notification payload for iOS. Android clients will receive this data under the field name pushData. (Optional)
     * @param isPersisted: Determines whether older clients should store the message if they do not support the new custom message type. 0 means do not store, 1 means store. Defaults to 1. (Optional)
     * @param isCounted: Determines whether older clients should count the message as unread if they do not support the new custom message type. 0 means do not count, 1 means count. Defaults to 1, incrementing the unread message count by 1. (Optional)
     * @param isIncludeSender: Indicates whether the sender also receives the message. 0 means not to receive, 1 means to receive. Default is 0 (optional).
     */
    public GroupMessage(String senderId, String[] targetId, String[] toUserId, String objectName, BaseMessage content,
                        String pushContent, String pushData, Integer isIncludeSender, Integer isPersisted, Integer isCounted,
                        Integer contentAvailable) {
        super(senderId, targetId, objectName, content, pushContent, pushData);
        this.isIncludeSender = isIncludeSender;
        this.isPersisted = isPersisted;
        //this.isCounted = isCounted;
        this.contentAvailable = contentAvailable;
        this.toUserId = toUserId;
    }

    public GroupMessage(String senderId, String[] targetId, String[] toUserId, String objectName, BaseMessage content,
                        String pushContent, String pushData, String pushExt, Integer isIncludeSender, Integer isPersisted,
                        Integer isCounted, Integer contentAvailable) {
        super(senderId, targetId, objectName, content, pushContent, pushData, pushExt);
        this.isIncludeSender = isIncludeSender;
        this.isPersisted = isPersisted;
        //this.isCounted = isCounted;
        this.contentAvailable = contentAvailable;
        this.toUserId = toUserId;
    }

    @Override
    public GroupMessage setSenderId(String senderId) {
        super.setSenderId(senderId);
        return this;
    }

    /**
     * Gets the target group ID.
     *
     * @return String
     */
    @Override
    public String[] getTargetId() {
        return super.getTargetId();
    }

    /**
     * Sets the target group ID.
     *
     * @return String
     */
    @Override
    public GroupMessage setTargetId(String[] targetId) {
        super.setTargetId(targetId);
        return this;
    }

    @Override
    public GroupMessage setContent(BaseMessage content) {
        super.setContent(content);
        return this;
    }

    @Override
    public GroupMessage setPushContent(String pushContent) {
        super.setPushContent(pushContent);
        return this;
    }

    @Override
    public GroupMessage setPushData(String pushData) {
        super.setPushData(pushData);
        return this;
    }

    @Override
    public GroupMessage setPushExt(String pushExt) {
        super.setPushExt(pushExt);
        return this;
    }

    @Override
    public GroupMessage setPushExt(PushExt pe) {
        super.setPushExt(pe);
        return this;
    }

    public Integer getIsPersisted() {
        return this.isPersisted;
    }

    public GroupMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    /*public Integer getIsCounted() {
        return this.isCounted;
    }

    public GroupMessage setIsCounted(Integer isCounted) {
        this.isCounted = isCounted;
        return this;
    }*/

    public Integer getIsIncludeSender() {
        return this.isIncludeSender;
    }

    public GroupMessage setIsIncludeSender(Integer isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
        return this;
    }

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public GroupMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }

    @Override
    public GroupMessage setObjectName(String objectName) {
        super.setObjectName(objectName);
        return this;
    }

    public String[] getToUserId() {
        return toUserId;
    }

    public GroupMessage setToUserId(String[] toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    public Boolean getDisablePush() {   return disablePush;  }

    public GroupMessage setDisablePush(Boolean disablePush) {
        this.disablePush = disablePush;
        return this;
    }

    public Boolean getExpansion() {   return expansion;  }

    public GroupMessage setExpansion(Boolean expansion) {
        this.expansion = expansion;
        return this;
    }

    @Override
    public GroupMessage setMsgRandom(Long msgRandom) {
        super.setMsgRandom(msgRandom);
        return this;
    }

    public Integer getIsMentioned() {
        return isMentioned;
    }

    public GroupMessage setIsMentioned(Integer isMentioned) {
        this.isMentioned = isMentioned;
        return this;
    }

    public HashMap<String, String> getExtraContent() {
        return extraContent;
    }

    public GroupMessage setExtraContent(HashMap<String, String> extraContent) {
        this.extraContent = extraContent;
        return this;
    }
}
