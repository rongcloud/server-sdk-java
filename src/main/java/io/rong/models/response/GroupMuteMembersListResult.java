package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

import java.util.List;

public class GroupMuteMembersListResult extends Result {
    // List of muted users in the group.
    List<GagGroupUser> members;

    public GroupMuteMembersListResult(Integer code, String msg, List<GagGroupUser> members) {
        super(code, msg);
        this.members = members;
    }

    public GroupMuteMembersListResult(List<GagGroupUser> members) {
        this.members = members;
    }

    /**
     * Get the list of muted members.
     *
     * @return List
     */
    public List<GagGroupUser> getMembers() {
        return this.members;
    }

    /**
     * Set the list of muted members.
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
