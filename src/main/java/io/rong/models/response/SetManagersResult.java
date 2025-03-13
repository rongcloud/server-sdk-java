package io.rong.models.response;


import io.rong.util.GsonUtil;

import java.util.List;

/**
 * Result of setting group administrators
 * @author RongCloud
 */
public class SetManagersResult extends ResponseResult {

    private List<String> userIds;
    private Integer managerCount;

    public SetManagersResult(Integer code, String msg) {
        super(code, msg);
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public SetManagersResult setUserIds(List<String> userIds) {
        this.userIds = userIds;
        return this;
    }

    public Integer getManagerCount() {
        return managerCount;
    }

    public SetManagersResult setManagerCount(Integer managerCount) {
        this.managerCount = managerCount;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, SetManagersResult.class);
    }
}
