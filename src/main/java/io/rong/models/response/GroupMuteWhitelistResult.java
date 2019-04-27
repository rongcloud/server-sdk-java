package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

public class GroupMuteWhitelistResult extends Result {
    private String[] userIds;

    public String[] getUserIds() {
        return userIds;
    }

    public void setUserIds(String[] userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, GroupBanWhitelistResult.class);
    }
}
