package io.rong.methods.message.ultragroup;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;
import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.message.*;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * 发送超级群消息方法
 * <p>
 * docs :
 *
 * @author RongCloud
 */
public class UltraGroup {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "message/ultragroup";
    private static final String RECAL_PATH = "message/recall";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public UltraGroup(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }

    /**
     * 发送超级群消息方法（以一个用户身份向群组发送消息，单条消息最大 128k.每秒钟最多发送 20 条消息，每次最多向 3 个群组发送，如：一次向 3 个群组发送消息，示为 3 条消息。）
     *
     * @param message
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult send(UltraGroupMessage message) throws Exception {

        String code = CommonUtil.checkFiled(message, PATH, CheckMethod.PUBLISH);
        if (null != code) {
            return (ResponseResult) GsonUtil.fromJson(code, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));

        for (int i = 0; i < message.getTargetId().length; i++) {
            String child = message.getTargetId()[i];
            if (null != child) {
                sb.append("&toGroupIds=").append(URLEncoder.encode(child, UTF8));
            }
        }

        sb.append("&objectName=").append(URLEncoder.encode(message.getContent().getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(message.getContent().toString(), UTF8));

        if (message.getPushContent() != null) {
            sb.append("&pushContent=").append(URLEncoder.encode(message.getPushContent().toString(), UTF8));
        }

        if (message.getPushData() != null) {
            sb.append("&pushData=").append(URLEncoder.encode(message.getPushData(), UTF8));
        }

        if (message.getPushExt() != null) {
            sb.append("&pushExt=").append(URLEncoder.encode(message.getPushExt(), UTF8));
        }

        if (message.getIsPersisted() != null) {
            sb.append("&isPersisted=").append(URLEncoder.encode(message.getIsPersisted().toString(), UTF8));
        }

        if (message.getBusChannel() != null) {
            sb.append("&busChannel=").append(URLEncoder.encode(message.getBusChannel().toString(), UTF8));
        }
        if (message.getContentAvailable() != null) {
            sb.append("&contentAvailable=").append(URLEncoder.encode(message.getContentAvailable().toString(), UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/group/publish.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        ResponseResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.PUBLISH, response), ResponseResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ResponseResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

}
