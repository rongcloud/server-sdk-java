package io.rong.models.response;

import io.rong.util.GsonUtil;

public class UserList {
    /**
     * 返回码，200 为正常。
     *
     */
    private Integer code;
    /**
     * 黑名单用户列表
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
