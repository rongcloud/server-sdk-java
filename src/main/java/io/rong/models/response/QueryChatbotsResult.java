package io.rong.models.response;


import io.rong.models.bot.ChatbotInfoModel;
import io.rong.util.GsonUtil;

import java.util.List;


/**
 * @author RongCloud
 */
public class QueryChatbotsResult extends ResponseResult {
    private List<ChatbotInfoModel> bots;

    public List<ChatbotInfoModel> getBots() {
        return bots;
    }

    public void setBots(List<ChatbotInfoModel> bots) {
        this.bots = bots;
    }

    public QueryChatbotsResult(Integer code, String msg) {
        super(code, msg);
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, QueryChatbotsResult.class);
    }
}
