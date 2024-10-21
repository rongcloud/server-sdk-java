package io.rong.models.response;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * @author RongCloud
 */
public class PagingQueryMembersResult extends PageResult {

    private List<GroupMember> members;

    public PagingQueryMembersResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public List<GroupMember> getMembers() {
        return members;
    }

    public void setMembers(List<GroupMember> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PagingQueryMembersResult.class);
    }

}
