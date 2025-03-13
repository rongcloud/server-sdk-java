package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * Batch add sensitive words
 */
public class BatchAddSensitiveWordResult extends Result {

    /**
     * Remaining quota
     */
    private Integer remainQuota;

    public BatchAddSensitiveWordResult(Integer code, String msg) {
        super(code, msg);
        this.code = code;
        this.errorMessage = msg;
    }

    public Integer getRemainQuota() {
        return remainQuota;
    }

    public BatchAddSensitiveWordResult setRemainQuota(Integer remainQuota) {
        this.remainQuota = remainQuota;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, BatchAddSensitiveWordResult.class);
    }
}
