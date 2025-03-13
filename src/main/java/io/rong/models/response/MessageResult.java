package io.rong.models.response;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * Push notification response result.
 */
public class MessageResult extends ResponseResult {

    /**
     * List of Message UIDs for one-to-one chat, group chat, chatroom, and ultra group messages.
     */
    private List<MessageUIDEntry> messageUIDs;

    public MessageResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }


    public List<MessageUIDEntry> getMessageUIDs() {
        return messageUIDs;
    }

    public MessageResult setMessageUIDs(List<MessageUIDEntry> messageUIDs) {
        this.messageUIDs = messageUIDs;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, MessageResult.class);
    }


}