package io.rong.example.message;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.message.history.History;
import io.rong.models.history.QueryHistoryMessageModel;
import io.rong.models.response.QueryHistoryMessageResult;

/**
 * Message sending example
 *
 * @author RongCloud
 * @version 3.0.0
 */
public class QueryHistoryMessageExample {
    /**
     * Replace with your App Key
     */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret
     */
    private static final String appSecret = "appSecret";

    /**
     * Custom API endpoint
     */
    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
        History history = rongCloud.message.history;

        /**
         * Query the history message of one-to-one chat
         */
        QueryHistoryMessageModel model = new QueryHistoryMessageModel();
        model.setUserId("user_01");
        model.setTargetId("user_02");
        model.setStartTime(1743584071876L);
        model.setEndTime(1743584071077L);
        model.setPageSize(50);
//        model.setIncludeStart(true);
        QueryHistoryMessageResult queryHistoryMessageResult = history.queryPrivateHistoryMessage(model);
        System.out.println(queryHistoryMessageResult.toString());

    }
}
