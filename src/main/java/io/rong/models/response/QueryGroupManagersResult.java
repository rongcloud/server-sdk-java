package io.rong.models.response;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * @author RongCloud
 */
public class QueryGroupManagersResult extends ResponseResult {

    private String groupId;

    private List<GroupManger> managers;

    public QueryGroupManagersResult(Integer code, String msg) {
        super(code, msg);
    }

    public String getGroupId() {
        return groupId;
    }

    public QueryGroupManagersResult setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public List<GroupManger> getManagers() {
        return managers;
    }

    public QueryGroupManagersResult setManagers(List<GroupManger> managers) {
        this.managers = managers;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, QueryGroupManagersResult.class);
    }
}
