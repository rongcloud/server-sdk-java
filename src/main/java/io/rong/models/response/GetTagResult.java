package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * Result of getTag operation.
 */
public class GetTagResult extends Result {

    /**
     * Array of all tags associated with the user.
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