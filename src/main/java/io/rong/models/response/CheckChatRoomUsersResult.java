package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

import java.util.List;

public class CheckChatRoomUsersResult extends Result {

    private List<ChatRoomUserInChrm> result;

    public List<ChatRoomUserInChrm> getResult() {
        return result;
    }

    public void setResult(List<ChatRoomUserInChrm> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, CheckChatRoomUsersResult.class);
    }


    static class ChatRoomUserInChrm{
        private String userid;
        private int isInChrm;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public int getIsInChrm() {
            return isInChrm;
        }

        public void setIsInChrm(int isInChrm) {
            this.isInChrm = isInChrm;
        }
    }
}
