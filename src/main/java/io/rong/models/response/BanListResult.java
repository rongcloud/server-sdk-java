package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.user.UserModel;
import io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class BanListResult extends Result {
    /**
     * User list
     */
    String[] users;

    /**
     * Total count
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
     * Get users
     *
     * @return User[]
     */
    public String[] getUsers() {
        return this.users;
    }
    /**
     * Set users
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
