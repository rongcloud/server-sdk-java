package io.rong.models.response;

public class CheckChatRoomUserResult {
    public String code;
    public Boolean isInChrm;

    public CheckChatRoomUserResult(String code, Boolean isInChrm) {
        this.code = code;
        this.isInChrm = isInChrm;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getInChrm() {
        return this.isInChrm;
    }

    public void setInChrm(Boolean inChrm) {
        this.isInChrm = inChrm;
    }
}
