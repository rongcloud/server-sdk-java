package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 *  http 成功返回带 status 的结果
 */
public class StatusResult  extends Result {

    private String status;
    private String reqBody;

    public StatusResult(Integer code, String msg) {
        super(code, msg);
        this.code = code;
        this.errorMessage = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReqBody() {
        return reqBody;
    }


    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, StatusResult.class);
    }
}
