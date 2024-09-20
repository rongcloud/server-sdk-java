package io.rong.models.response;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * @author RongCloud
 */
public class PagingQueryJoinedGroupsResult extends PageResult {

    private List<EntrustGroupProfile> groups;

    public PagingQueryJoinedGroupsResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public List<EntrustGroupProfile> getGroups() {
        return groups;
    }

    public void setGroups(List<EntrustGroupProfile> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PagingQueryJoinedGroupsResult.class);
    }

}
