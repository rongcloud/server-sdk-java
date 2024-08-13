package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * push 返回结果
 */
public class PushResult extends Result {

    /**
     * 推送唯一标识。
     */
    private String id;

    /**
     * 推送消息ID
     */
    private String messageUID;

    private String reqBody;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReqBody() {
        return reqBody;
    }


    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    public PushResult(Integer code, String id) {
        super(code, id);
        this.code = code;
        this.id = id;
    }

    public String getMessageUID() {
        return messageUID;
    }

    public PushResult setMessageUID(String messageUID) {
        this.messageUID = messageUID;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PushResult.class);
    }


}