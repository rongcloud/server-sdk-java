package io.rong.models.group;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

public class GroupMemberCount extends Result {

    private int memberCount;

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, GroupMemberCount.class);
    }
}
