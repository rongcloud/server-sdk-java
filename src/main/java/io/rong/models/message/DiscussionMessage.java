package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * Discussion group message body.
 *
 * @author hc
 */
public class DiscussionMessage extends MessageModel {

    /**
     * For iOS platform, controls the display count of unread messages in push notifications. 
     * Only effective when toUserId is a single user ID. (Optional)
     */
    public Integer isPersisted;
    /**
     * When a new custom message is introduced in the current version but not available in older versions, 
     * this flag determines whether older clients should count the message as unread. 
     * 0 means do not count, 1 means count. Default is 1, which increments the unread message count by 1. (Optional)
     */
    public Integer isCounted;

    /**
     * When a new custom message is introduced in the current version but not available in older versions, 
     * this flag determines whether older clients should count the message as unread. 
     * 0 means do not count, 1 means count. Default is 1, which increments the unread message count by 1. (Optional)
     */
    private Integer isIncludeSender;

    /**
     * iOS silent push notification. 0 means off, 1 means on.
     **/
    public Integer contentAvailable;

    public DiscussionMessage() {
    }

    public DiscussionMessage(String senderId, String[] targetId, String objectName, BaseMessage content,
                             String pushContent, String pushData, Integer isPersisted, Integer isCounted, Integer isIncludeSender,
                             Integer contentAvailable) {
        super(senderId, targetId, objectName, content, pushContent, pushData);
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.isIncludeSender = isIncludeSender;
        this.contentAvailable = contentAvailable;
    }

    public DiscussionMessage(String senderId, String[] targetId, String objectName, BaseMessage content,
                             String pushContent, String pushData, String pushExt, Integer isPersisted, Integer isCounted,
                             Integer isIncludeSender, Integer contentAvailable) {
        super(senderId, targetId, objectName, content, pushContent, pushData, pushExt);
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.isIncludeSender = isIncludeSender;
        this.contentAvailable = contentAvailable;
    }

    @Override
    public DiscussionMessage setSenderId(String senderId) {
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
    public DiscussionMessage setTargetId(String[] targetId) {
        super.setTargetId(targetId);
        return this;
    }

    @Override
    public DiscussionMessage setContent(BaseMessage content) {
        super.setContent(content);
        return this;
    }

    @Override
    public DiscussionMessage setPushContent(String pushContent) {
        super.setPushContent(pushContent);
        return this;
    }

    @Override
    public DiscussionMessage setPushData(String pushData) {
        super.setPushData(pushData);
        return this;
    }

    @Override
    public DiscussionMessage setPushExt(String pushExt) {
        super.setPushExt(pushExt);
        return this;
    }

    @Override
    public DiscussionMessage setPushExt(PushExt pe) {
        super.setPushExt(pe);
        return this;
    }

    public Integer getIsPersisted() {
        return this.isPersisted;
    }

    public DiscussionMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    public Integer getIsCounted() {
        return this.isCounted;
    }

    public DiscussionMessage setIsCounted(Integer isCounted) {
        this.isCounted = isCounted;
        return this;
    }

    @Override
    public DiscussionMessage setObjectName(String objectName) {
        super.setObjectName(objectName);
        return this;
    }

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public DiscussionMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }

    public Integer getIsIncludeSender() {
        return this.isIncludeSender;
    }

    public DiscussionMessage setIsIncludeSender(Integer isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
        return this;
    }
}
