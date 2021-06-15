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

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PushResult.class);
    }


}