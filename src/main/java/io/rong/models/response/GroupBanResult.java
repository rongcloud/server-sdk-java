package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

public class GroupBanResult extends Result {
    private GroupBanInfo[] groupinfo;

    public GroupBanInfo[] getGroupinfo() {
        return groupinfo;
    }

    public void setGroupinfo(GroupBanInfo[] groupinfo) {
        this.groupinfo = groupinfo;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, GroupBanResult.class);
    }
}
