package io.rong.models.response;

import io.rong.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Query history message
 */
public class QueryHistoryMessageResult extends ResponseResult {

    private List<HistoryMessage> data = new ArrayList<>();

    public QueryHistoryMessageResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public List<HistoryMessage> getData() {
        return data;
    }

    public QueryHistoryMessageResult setData(List<HistoryMessage> data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, QueryHistoryMessageResult.class);
    }

}
