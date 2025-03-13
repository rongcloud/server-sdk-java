package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.user.UserModel;
import io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class PWhiteListResult extends Result {
    /**
     * List of users in the one-to-one chat allowlist.
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
     * Get the users.
     *
     * @return User[]
     */
    public UserModel[] getUsers() {
        return this.users;
    }
    /**
     * Set the users.
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
