package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

public class CheckChatRoomUserResult  extends Result {
    public Boolean isInChrm;

    public CheckChatRoomUserResult(Integer code, Boolean isInChrm) {
        super(code, "");
        this.isInChrm = isInChrm;
    }

    public Boolean getInChrm() {
        return this.isInChrm;
    }

    public void setInChrm(Boolean inChrm) {
        this.isInChrm = inChrm;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, CheckChatRoomUserResult.class);
    }
}
