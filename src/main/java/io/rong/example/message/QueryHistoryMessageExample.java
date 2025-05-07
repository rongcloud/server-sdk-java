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
        model.setIncludeStart(true);
        QueryHistoryMessageResult queryHistoryMessageResult = history.queryPrivateHistoryMessage(model);
        System.out.println(queryHistoryMessageResult.toString());



        /**
         * Query the history message of group chat
         */
        QueryHistoryMessageModel groupInput = new QueryHistoryMessageModel();
        groupInput.setUserId("user_01");
        groupInput.setTargetId("groupId");
        groupInput.setStartTime(1743584071876L);
        groupInput.setEndTime(1743584071077L);
        groupInput.setPageSize(50);
        groupInput.setIncludeStart(true);
        QueryHistoryMessageResult groupResult = history.queryGroupHistoryMessage(groupInput);
        System.out.println(groupResult.toString());


        /**
         * Query the history message of ultra group chat
         */
        QueryHistoryMessageModel ugInput = new QueryHistoryMessageModel();
        ugInput.setUserId("user_01");
        ugInput.setTargetId("ug_id");
        ugInput.setBusChannel("busChannel");
        ugInput.setStartTime(1743584071876L);
        ugInput.setEndTime(1743584071077L);
        ugInput.setPageSize(50);
        ugInput.setIncludeStart(true);
        QueryHistoryMessageResult ugResult = history.queryUltraGroupHistoryMessage(ugInput);
        System.out.println(ugResult.toString());


        /**
         * Query the history message of chat room.
         */
        QueryHistoryMessageModel chatInput = new QueryHistoryMessageModel();
        chatInput.setUserId("user_01");
        chatInput.setTargetId("user_02");
        chatInput.setStartTime(1743584071876L);
        chatInput.setEndTime(1743584071077L);
        chatInput.setPageSize(50);
        chatInput.setIncludeStart(true);
        QueryHistoryMessageResult chatResult = history.queryChatroomHistoryMessage(chatInput);
        System.out.println(chatResult.toString());

    }
}
