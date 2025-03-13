package io.rong.models.response;

import io.rong.models.BlockUsers;
import io.rong.models.Result;
import io.rong.util.GsonUtil;

import java.util.List;

public class BlockUserResult extends Result {
    // List of banned users.
    List<BlockUsers> users;

    public BlockUserResult(Integer code, String errorMessage, List<BlockUsers> users) {
        super(code, errorMessage);
        this.users = users;
    }
    /**
     * Set users
     *
     */
    public void setUsers(List<BlockUsers> users) {
        this.users = users;
    }

    /**
     * Get users
     *
     * @return List
     */
    public List<BlockUsers> getUsers() {
        return users;
    }
    @Override
    public String toString() {
        return GsonUtil.toJson(this, BlockUserResult.class);
    }

}
