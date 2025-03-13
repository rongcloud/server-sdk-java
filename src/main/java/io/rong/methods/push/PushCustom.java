package io.rong.methods.push;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;
import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.push.BroadcastModel;
import io.rong.models.push.PushModel;
import io.rong.models.response.PushResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;

/**
 * Push Plus service
 * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 *
 * @author lang
 */
public class PushCustom {

    private static final String PATH = "push/custom";
    private static final String URI = "/" + PATH + ".json";
    private static final String CONTENT_TYPE = "application/json";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public PushCustom(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
    }


    /**
     * Push Plus
     *
     * @param push/custom Push data
     * @return PushResult
     **/
    public PushResult pushcustom(PushModel push) throws Exception {
        String message = CommonUtil.checkFiled(push, PATH, CheckMethod.PUSHCUSTOM);
        if (null != message) {
            return (PushResult) GsonUtil.fromJson(message, PushResult.class);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, URI,
                CONTENT_TYPE);

        HttpUtil.setBodyParameter(push.toString(), conn, rongCloud.getConfig());

        PushResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (PushResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.PUSHCUSTOM, response), PushResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new PushResult(500, "");
            result.setErrorMessage("request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(push.toString());
        return result;
    }

}