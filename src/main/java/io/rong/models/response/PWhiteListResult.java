package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.user.UserModel;
import io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class PWhiteListResult extends Result {
    /**
     * 单聊白名单用户列表
     */
    UserModel[] users;

    public PWhiteListResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public PWhiteListResult(Integer code, String msg, UserModel[] users) {
        super(code, msg);
        this.users = users;
    }

    public PWhiteListResult(UserModel[] users) {
        this.users = users;
    }

    /**
     * 获取users
     *
     * @return User[]
     */
    public UserModel[] getUsers() {
        return this.users;
    }
    /**
     * 设置users
     *
     */
    public void setUsers(UserModel[] users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PWhiteListResult.class);
    }

}
