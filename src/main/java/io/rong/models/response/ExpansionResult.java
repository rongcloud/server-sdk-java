package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

import java.util.HashMap;

/**
 * 获取扩展信息
 */
public class ExpansionResult extends Result {

    private String reqBody;
    private HashMap<String, ExtraContent> extraContent;

    public ExpansionResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public ExpansionResult(Integer code, String errorMessage, HashMap<String, ExtraContent> extraContent) {
        super(code, errorMessage);
        this.extraContent = extraContent;
    }

    public String getReqBody() {
        return reqBody;
    }


    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }


    public HashMap<String, ExtraContent> getExtraContent() {
        return extraContent;
    }

    public void setExtraContent(HashMap<String, ExtraContent> extraContent) {
        this.extraContent = extraContent;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ExpansionResult.class);
    }

}
