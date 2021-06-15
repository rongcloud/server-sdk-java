package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.user.UserModel;
import io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class BanListResult extends Result {
    /**
     * 用户列表
     */
    String[] users;

    /**
     * 总数
     */
    Integer total;

    public BanListResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public BanListResult(Integer code, String msg, String[] users) {
        super(code, msg);
        this.users = users;
    }

    public BanListResult(String[] users) {
        this.users = users;
    }

    /**
     * 获取users
     *
     * @return User[]
     */
    public String[] getUsers() {
        return this.users;
    }
    /**
     * 设置users
     *
     */
    public void setUsers(String[] users) {
        this.users = users;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, BanListResult.class);
    }

}
