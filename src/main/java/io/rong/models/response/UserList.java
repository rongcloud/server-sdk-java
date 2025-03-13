package io.rong.models.response;

import io.rong.util.GsonUtil;

public class UserList {
    /**
     * Response code, 200 indicates success.
     *
     */
    private Integer code;
    /**
     * Blocklist user list
     */
    private String[] users;


    public UserList(Integer code, String[] users) {
        this.code = code;
        this.users = users;
    }

    public String[] getUsers() {
        return this.users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, UserList.class);
    }

}
