package io.rong.models.push;

import io.rong.util.GsonUtil;

/**
 * Message content to be sent (Required)
 */
public class Message {

    /**
     * The content of the message. Refer to the RongCloud Server API message type table for examples. If the objectName is a custom message type, this parameter can be customized. (Required)
     */
    private String content;

    /**
     * The message type. Refer to the RongCloud Server API message type table for message flags. Custom message types are allowed, with a length not exceeding 32 characters. (Required)
     */
    private String objectName;

    /**
     * Disables updating the last message in the conversation. When set to false, the sent message will appear in the conversation list; when set to true, it will not update the message content in the conversation list.
     * Note: This parameter only affects messages stored on the client side.
     */
    private Boolean disableUpdateLastMsg;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }


    public Boolean getDisableUpdateLastMsg() {
        return disableUpdateLastMsg;
    }

    public Message setDisableUpdateLastMsg(Boolean disableUpdateLastMsg) {
        this.disableUpdateLastMsg = disableUpdateLastMsg;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, Message.class);
    }
}