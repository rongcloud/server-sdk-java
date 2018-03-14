package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.User;
import io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class BlackListResult extends Result {
    /**
     * 黑名单用户列表
     */
    User[] users;

    public BlackListResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public BlackListResult(Integer code, String msg, User[] users) {
        super(code, msg);
        this.users = users;
    }
    /**
     * 获取users
     *
     * @return User[]
     */
    public User[] getUsers() {
        return this.users;
    }
    /**
     * 设置users
     *
     */
    public void setUsers(User[] users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, BlackListResult.class);
    }

}
