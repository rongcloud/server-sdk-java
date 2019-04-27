package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

import java.util.List;

public class GroupMuteMembersListResult extends Result {
    // 群组被禁言用户列表。
    List<GagGroupUser> members;

    public GroupMuteMembersListResult(Integer code, String msg, List<GagGroupUser> members) {
        super(code, msg);
        this.members = members;
    }

    public GroupMuteMembersListResult(List<GagGroupUser> members) {
        this.members = members;
    }

    /**
     * 获取members
     *
     * @return List
     */
    public List<GagGroupUser> getMembers() {
        return this.members;
    }

    /**
     * 设置members
     *
     */
    public void setMembers(List<GagGroupUser> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, GroupMuteMembersListResult.class);
    }
}
