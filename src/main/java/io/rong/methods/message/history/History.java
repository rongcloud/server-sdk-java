package io.rong.methods.message.history;

import io.rong.RongCloud;
import io.rong.methods.BaseMethod;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.history.QueryHistoryMessageModel;
import io.rong.models.response.HistoryMessageResult;
import io.rong.models.response.QueryHistoryMessageResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Message History Service
 *
 * @author RongCloud
 *
 */
public class History  extends BaseMethod {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "message/history";

    @Override
    protected void initPath() {
        super.path = PATH;
    }

    public History(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        initPath();
    }

    /**
     * Get Message History Download URL
     * This method retrieves the download URL for all conversation message records within a specified hour on a specific day in Beijing time. (Currently supports one-to-one chat, discussion group, group chat, chatroom, customer service, and system notification message history download)
     *
     * @param  date: Specifies a specific hour on a specific day in Beijing time, formatted as yyyyMMddHH, e.g., 2014010101 represents 1:00 AM on January 1, 2014. (Required)
     *
     * @return HistoryMessageResult
     * @throws Exception
     **/
    public Result get(String date) throws Exception {
        if (date == null) {
            return new ResponseResult(1002, "Paramer 'date' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&date=").append(URLEncoder.encode(date.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/history.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (HistoryMessageResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GET, HttpUtil.returnResult(conn, rongCloud.getConfig())), HistoryMessageResult.class);
    }

    /**
     * Message history deletion method (Deletes all conversation message records within a specified hour on a specific day in the app. After successfully calling this interface, the message record file for the specified hour in the date parameter will be permanently deleted within 5-10 minutes.)
     *
     * @param  date: Specifies a certain hour on a certain day in Beijing time, formatted as 2014010101, representing 1:00 AM on January 1, 2014. (Required)
     *
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult remove(String date) throws Exception {
        if (date == null) {
            return new ResponseResult(1002,"Paramer 'date' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&date=").append(URLEncoder.encode(date.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/history/delete.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

/**
 * Clear history messages -
 * If the user has enabled the Cloud Storage for One-to-One and Group Messages service, this interface can be used to clear the historical messages stored on the server before a specified time for a specific user by conversation. After clearing, the user will no longer be able to retrieve the historical messages stored on the RongCloud server from the client. Please execute this operation with caution.
 *
 **/


    public ResponseResult clean(String conversationType, String fromUserId, String targetId, String msgTimestamp) throws Exception {
        if (StringUtils.isBlank(conversationType) || StringUtils.isBlank(fromUserId) || StringUtils.isBlank(targetId)) {
            return new ResponseResult(1002,"Paramer 'conversationType', 'fromUserId', 'targetId' is required");
        }

		StringBuilder sb = new StringBuilder();
		sb.append("&conversationType=").append(URLEncoder.encode(conversationType, UTF8));
		sb.append("&fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
		sb.append("&targetId=").append(URLEncoder.encode(targetId, UTF8));

		if (StringUtils.isNotBlank(msgTimestamp)) {
			sb.append("&msgTimestamp=").append(URLEncoder.encode(msgTimestamp, UTF8));
		}

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/conversation/message/history/clean.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.CLEAN, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * Query the history message of one-to-one chat
     **/
    public QueryHistoryMessageResult queryPrivateHistoryMessage(QueryHistoryMessageModel model) throws Exception {
        String method = CheckMethod.QUERY_HISTORY_MSG;
        QueryHistoryMessageResult result = checkFiled(model, method, QueryHistoryMessageResult.class);
        if (result != null) {
            return result;
        }
        return doRequest("/v3/message/private/query.json", model.toString(), method, QueryHistoryMessageResult.class, CONTENT_TYPE_JSON);
    }

}
