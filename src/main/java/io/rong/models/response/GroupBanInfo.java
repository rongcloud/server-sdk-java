package io.rong.models.response;

import io.rong.util.GsonUtil;

public class GroupBanInfo {
    private String groupId;
    private Integer stat;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, GroupBanInfo.class);
    }
}
