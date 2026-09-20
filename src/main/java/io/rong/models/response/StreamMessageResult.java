package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * Stream message response result.
 */
public class StreamMessageResult extends ResponseResult {

    private String messageUID;

    public StreamMessageResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public String getMessageUID() {
        return messageUID;
    }

    public void setMessageUID(String messageUID) {
        this.messageUID = messageUID;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, StreamMessageResult.class);
    }
}
