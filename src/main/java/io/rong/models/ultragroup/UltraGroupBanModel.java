package io.rong.models.ultragroup;

import io.rong.models.Result;
import io.rong.models.response.GroupBanInfo;
import io.rong.util.GsonUtil;

public class UltraGroupBanModel extends Result {
    private GroupBanInfo[] groupinfo;

    public GroupBanInfo[] getGroupinfo() {
        return groupinfo;
    }

    public void setGroupinfo(GroupBanInfo[] groupinfo) {
        this.groupinfo = groupinfo;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, UltraGroupBanModel.class);
    }
}
