package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class PageResult extends ResponseResult {

    private Integer totalCount;
    private String pageToken;

    public PageResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PageResult.class);
    }

}
