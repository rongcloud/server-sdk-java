package io.rong.models.response.group;

import io.rong.util.GsonUtil;
import io.rong.models.response.ResponseResult;

/**
 * 用户指定群组名称备注名获取接口返回实体
 */
public class AliasResult extends ResponseResult {

    private String remarkName;

    public AliasResult(Integer code, String msg) {
        super(code, msg);
        this.code = code;
        this.errorMessage = msg;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, AttentionResult.class);
    }
}
