package io.rong.models.history;


import io.rong.util.GsonUtil;

public class QueryHistoryMessageModel {

    /**
     * Required parameters, query user ID
     */
    private String userId;
    /**
     * Required parameters, conversation id
     */
    private String targetId;
    /**
     * Required parameters, start time (millisecond timestamp)
     */
    private Long startTime;
    /**
     * Required parameters, end time (millisecond timestamp);
     */
    private Long endTime;

    /**
     * Non-mandatory parameters, number of queries; range: 1-100; default 10.
     * If the query time is less than the number of messages entered, the query time range shall prevail.
     */
    private Integer pageSize;
    /**
     * Required parameters, whether to include the start time message, true includes, false does not include.
     */
    private Boolean includeStart;

    public String getUserId() {
        return userId;
    }

    public QueryHistoryMessageModel setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getTargetId() {
        return targetId;
    }

    public QueryHistoryMessageModel setTargetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public Long getStartTime() {
        return startTime;
    }

    public QueryHistoryMessageModel setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public Long getEndTime() {
        return endTime;
    }

    public QueryHistoryMessageModel setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public QueryHistoryMessageModel setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Boolean getIncludeStart() {
        return includeStart;
    }

    public QueryHistoryMessageModel setIncludeStart(Boolean includeStart) {
        this.includeStart = includeStart;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, QueryHistoryMessageModel.class);
    }


}
