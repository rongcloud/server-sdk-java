package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * getTag 返回结果
 */
public class GetTagResult extends Result {

    /**
     * 用户所有的标签数组。
     */
    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, GetTagResult.class);
    }
}