package io.rong.models.message;

import io.rong.messages.BaseMessage;
import java.util.HashMap;

/**
 * One-to-one chat message body
 *
 * @author RongCloud
 */
public class PrivateMessage extends MessageModel {

    /**
     * For iOS platform, controls the display count of unread messages during push notifications. Only effective when toUserId is a single user ID. The client retrieves the remote push content as badge. -1 means the badge count remains unchanged. Specifying a number changes the badge count to the specified number, with a maximum of 9999. (Optional)
     **/
    public String count;
    /**
     * For iOS platform, controls the display count of unread messages during push notifications. Only effective when toUserId is a single user ID. (Optional)
     */
    public Integer isPersisted;
    /**
     * When a new custom message is introduced in the current version and the older version does not support it, this parameter determines whether the older client counts the message as unread. 0 means not counted, 1 means counted. Default is 1, increasing the unread message count by 1. (Optional)
     */
    public Integer isCounted;

    /**
     * Whether to filter the sender's blocklist. 0 means not filtered, 1 means filtered. Default is 0, not filtered. (Optional)
     */
    public Integer verifyBlacklist;
    /**
     * Whether the sender receives the message themselves. 0 means not received, 1 means received. Default is 0, not received. (Optional)
     */
    public Integer isIncludeSender;

    public Integer contentAvailable;

    /**
     * Whether the message is silent. Default is false. When set to true, end users will not receive notification reminders when offline. (Optional). Not supported in global data centers.
     */
    public Boolean disablePush;

    /**
     * Whether the message is extensible. Default is false. When set to true, the client can set extended information for this message after receiving it. (Optional). Not supported in global data centers.
     */
    public Boolean expansion;

    /**
     * Extended message content, effective when expansion is true.
     */
    public HashMap<String, String> extraContent;

    /**
     * Whether the message need readReceipt; 0 means not needed, 1 means need. Default is 0.
     */
    public Integer needReadReceipt;

    public PrivateMessage() {
    }

    public PrivateMessage(String senderId, String[] targetId, String objectName, BaseMessage content,
        String pushContent, String pushData, String count, Integer isPersisted, Integer isCounted,
        Integer verifyBlacklist, Integer isIncludeSender, Integer contentAvailable) {
        super(senderId, targetId, objectName, content, pushContent, pushData);
        this.count = count;
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.verifyBlacklist = verifyBlacklist;
        this.isIncludeSender = isIncludeSender;
        this.contentAvailable = contentAvailable;
    }

    public PrivateMessage(String senderId, String[] targetId, String objectName, BaseMessage content,
        String pushContent, String pushData, String pushExt, String count, Integer isPersisted, Integer isCounted,
        Integer verifyBlacklist, Integer isIncludeSender, Integer contentAvailable) {
        super(senderId, targetId, objectName, content, pushContent, pushData, pushExt);
        this.count = count;
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.verifyBlacklist = verifyBlacklist;
        this.isIncludeSender = isIncludeSender;
        this.contentAvailable = contentAvailable;
    }

    @Override
    public PrivateMessage setSenderId(String senderId) {
        super.setSenderId(senderId);
        return this;
    }

    /**
     * Get the recipient user ID
     *
     * @return String
     */
    @Override
    public String[] getTargetId() {
        return super.getTargetId();
    }

    /**
     * Set the recipient user ID
     */
    @Override
    public PrivateMessage setTargetId(String[] targetId) {
        super.setTargetId(targetId);
        return this;
    }

    @Override
    public PrivateMessage setContent(BaseMessage content) {
        super.setContent(content);
        return this;
    }

    @Override
    public PrivateMessage setPushContent(String pushContent) {
        super.setPushContent(pushContent);
        return this;
    }

    @Override
    public PrivateMessage setPushData(String pushData) {
        super.setPushData(pushData);
        return this;
    }

    @Override
    public PrivateMessage setPushExt(String pushExt) {
        super.setPushExt(pushExt);
        return this;
    }

    @Override
    public PrivateMessage setPushExt(PushExt pe) {
        super.setPushExt(pe);
        return this;
    }

    public String getCount() {
        return this.count;
    }

    public PrivateMessage setCount(String count) {
        this.count = count;
        return this;
    }

    public Integer getVerifyBlacklist() {
        return this.verifyBlacklist;
    }

    public PrivateMessage setVerifyBlacklist(Integer verifyBlacklist) {
        this.verifyBlacklist = verifyBlacklist;
        return this;
    }

    public Integer getIsPersisted() {
        return this.isPersisted;
    }

    public PrivateMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    public Integer getIsCounted() {
        return this.isCounted;
    }

    public PrivateMessage setIsCounted(Integer isCounted) {
        this.isCounted = isCounted;
        return this;
    }

    public Integer getIsIncludeSender() {
        return this.isIncludeSender;
    }

    public PrivateMessage setIsIncludeSender(Integer isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
        return this;
    }

    public Boolean getDisablePush() {   return disablePush;  }

    public PrivateMessage setDisablePush(Boolean disablePush) {
        this.disablePush = disablePush;
        return this;
    }

    public Boolean getExpansion() {   return expansion;  }

    public PrivateMessage setExpansion(Boolean expansion) {
        this.expansion = expansion;
        return this;
    }

    @Override
    public PrivateMessage setObjectName(String objectName) {
        super.setObjectName(objectName);
        return this;
    }

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public PrivateMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }

    @Override
    public PrivateMessage setMsgRandom(Long msgRandom) {
        super.setMsgRandom(msgRandom);
        return this;
    }

    public HashMap<String, String> getExtraContent() {
        return extraContent;
    }

    public PrivateMessage setExtraContent(HashMap<String, String> extraContent) {
        this.extraContent = extraContent;
        return this;
    }

    public Integer getNeedReadReceipt() {
        return needReadReceipt;
    }

    public PrivateMessage setNeedReadReceipt(Integer needReadReceipt) {
        this.needReadReceipt = needReadReceipt;
        return this;
    }
}
