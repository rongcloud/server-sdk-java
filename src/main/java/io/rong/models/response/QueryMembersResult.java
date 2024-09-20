package io.rong.models.response;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * @author RongCloud
 */
public class QueryMembersResult extends ResponseResult {

    private List<GroupMember> members;

    public QueryMembersResult(Integer code, String errorMessage) {
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
        return GsonUtil.toJson(this, QueryMembersResult.class);
    }

}
