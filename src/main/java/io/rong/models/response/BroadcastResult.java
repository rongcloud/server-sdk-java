package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * Push notification response result.
 */
public class BroadcastResult extends ResponseResult {

    /**
     * The Message UID of the broadcast message.
     */
    private String messageUID;


    public BroadcastResult(Integer code, String msg) {
        super(code, msg);
    }


    public String getMessageUID() {
        return messageUID;
    }

    public BroadcastResult setMessageUID(String messageUID) {
        this.messageUID = messageUID;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, BroadcastResult.class);
    }


}