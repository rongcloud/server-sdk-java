package io.rong.models.response;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * @author RongCloud
 */
public class PagingQueryGroupsResult extends ResponseResult {

    private String pageToken;
    private List<EntrustGroupInfo> groups;

    public PagingQueryGroupsResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public String getPageToken() {
        return pageToken;
    }

    public PagingQueryGroupsResult setPageToken(String pageToken) {
        this.pageToken = pageToken;
        return this;
    }

    public List<EntrustGroupInfo> getGroups() {
        return groups;
    }

    public void setGroups(List<EntrustGroupInfo> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PagingQueryGroupsResult.class);
    }

}
