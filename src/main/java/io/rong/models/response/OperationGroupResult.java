package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * Operation group returns results
 */
public class OperationGroupResult extends ResponseResult {

    /**
     * Message UID.
     */
    private String messageUID;

    public OperationGroupResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public String getMessageUID() {
        return messageUID;
    }

    public OperationGroupResult setMessageUID(String messageUID) {
        this.messageUID = messageUID;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, OperationGroupResult.class);
    }


}