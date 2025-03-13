package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * Push notification result.
 */
public class PushResult extends Result {

    /**
     * The unique identifier of the push notification.
     */
    private String id;

    /**
     * The message ID of the push notification.
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