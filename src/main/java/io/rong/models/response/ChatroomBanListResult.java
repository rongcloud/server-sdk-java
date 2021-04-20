package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

public class ChatroomBanListResult extends Result{

    private String[] chatroomIds;
    private String reqBody;

    public ChatroomBanListResult(Integer code, String msg, String[] chatroomIds) {
        super(code, msg);
        this.chatroomIds = chatroomIds;
    }

    public String[] getChatroomIds() {
        return this.chatroomIds;
    }

    public void setChatroomIds(String[] chatroomIds) {
        this.chatroomIds = chatroomIds;
    }

    public String getReqBody() {
        return reqBody;
    }

    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ChatroomBanListResult.class);
    }
}
