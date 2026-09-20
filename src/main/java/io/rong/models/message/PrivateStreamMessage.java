package io.rong.models.message;

import io.rong.messages.StreamMessage;

import java.util.HashMap;

/**
 * Private stream message body.
 */
public class PrivateStreamMessage {

    private String fromUserId;
    private String toUserId;
    private String objectName;
    private StreamMessage content;
    private Integer isIncludeSender;
    private Integer isPersisted;
    private String extraContent;
    private Boolean disableUpdateLastMsg;

    public PrivateStreamMessage() {
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public PrivateStreamMessage setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }

    public String getToUserId() {
        return toUserId;
    }

    public PrivateStreamMessage setToUserId(String toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    public String getObjectName() {
        return objectName;
    }

    public PrivateStreamMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public StreamMessage getContent() {
        return content;
    }

    public PrivateStreamMessage setContent(StreamMessage content) {
        this.content = content;
        return this;
    }

    public Integer getIsIncludeSender() {
        return isIncludeSender;
    }

    public PrivateStreamMessage setIsIncludeSender(Integer isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
        return this;
    }

    public Integer getIsPersisted() {
        return isPersisted;
    }

    public PrivateStreamMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    public String getExtraContent() {
        return extraContent;
    }

    public PrivateStreamMessage setExtraContent(String extraContent) {
        this.extraContent = extraContent;
        return this;
    }

    public Boolean getDisableUpdateLastMsg() {
        return disableUpdateLastMsg;
    }

    public PrivateStreamMessage setDisableUpdateLastMsg(Boolean disableUpdateLastMsg) {
        this.disableUpdateLastMsg = disableUpdateLastMsg;
        return this;
    }

}
