package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * push 返回结果
 */
public class BroadcastResult extends ResponseResult {

    /**
     * 广播消息ID。
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