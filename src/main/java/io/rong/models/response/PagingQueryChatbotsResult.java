package io.rong.models.response;


import io.rong.models.bot.ChatbotInfoModel;
import io.rong.util.GsonUtil;

import java.util.List;


/**
 * @author RongCloud
 */
public class PagingQueryChatbotsResult extends ResponseResult {
    private Integer total;
    private Boolean hasNext;
    private List<ChatbotInfoModel> bots;


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<ChatbotInfoModel> getBots() {
        return bots;
    }

    public void setBots(List<ChatbotInfoModel> bots) {
        this.bots = bots;
    }

    public PagingQueryChatbotsResult(Integer code, String msg) {
        super(code, msg);
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PagingQueryChatbotsResult.class);
    }
}
