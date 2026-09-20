package io.rong.models.message;

import io.rong.messages.StreamMessage;

import java.util.HashMap;

/**
 * Group stream message body.
 */
public class GroupStreamMessage {

    private String fromUserId;
    private String toGroupId;
    private String[] toUserIds;
    private String objectName;
    private StreamMessage content;
    private Integer isIncludeSender;
    private Integer isPersisted;
    private Integer isMentioned;
    private String extraContent;
    private Boolean disableUpdateLastMsg;

    public GroupStreamMessage() {
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public GroupStreamMessage setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }

    public String getToGroupId() {
        return toGroupId;
    }

    public GroupStreamMessage setToGroupId(String toGroupId) {
        this.toGroupId = toGroupId;
        return this;
    }

    public String[] getToUserIds() {
        return toUserIds;
    }

    public GroupStreamMessage setToUserIds(String[] toUserIds) {
        this.toUserIds = toUserIds;
        return this;
    }

    public String getObjectName() {
        return objectName;
    }

    public GroupStreamMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public StreamMessage getContent() {
        return content;
    }

    public GroupStreamMessage setContent(StreamMessage content) {
        this.content = content;
        return this;
    }

    public Integer getIsIncludeSender() {
        return isIncludeSender;
    }

    public GroupStreamMessage setIsIncludeSender(Integer isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
        return this;
    }

    public Integer getIsPersisted() {
        return isPersisted;
    }

    public GroupStreamMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    public Integer getIsMentioned() {
        return isMentioned;
    }

    public GroupStreamMessage setIsMentioned(Integer isMentioned) {
        this.isMentioned = isMentioned;
        return this;
    }

    public String getExtraContent() {
        return extraContent;
    }

    public GroupStreamMessage setExtraContent(String extraContent) {
        this.extraContent = extraContent;
        return this;
    }

    public Boolean getDisableUpdateLastMsg() {
        return disableUpdateLastMsg;
    }

    public GroupStreamMessage setDisableUpdateLastMsg(Boolean disableUpdateLastMsg) {
        this.disableUpdateLastMsg = disableUpdateLastMsg;
        return this;
    }
}
