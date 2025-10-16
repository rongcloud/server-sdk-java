package io.rong.models.response;

import io.rong.util.GsonUtil;

import java.util.List;

public class QueryUsersResult extends ResponseResult {

    /**
     * Total number of users.
     */
    private Integer total;
    /**
     * Blocklist user list
     */
    private List<UserInfo> users;

    public QueryUsersResult(Integer code, String msg) {
        super(code, msg);
    }


    @Override
    public String toString() {
        return GsonUtil.toJson(this, QueryUsersResult.class);
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static class UserInfo {
        private String userId;
        private String userName;
        private String userPortrait;
        private String regTime;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPortrait() {
            return userPortrait;
        }

        public void setUserPortrait(String userPortrait) {
            this.userPortrait = userPortrait;
        }

        public String getRegTime() {
            return regTime;
        }

        public void setRegTime(String regTime) {
            this.regTime = regTime;
        }
    }


}
