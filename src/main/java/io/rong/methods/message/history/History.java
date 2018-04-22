package io.rong.methods.message.history;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.models.CheckMethod;
import io.rong.models.response.HistoryMessageResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 * 消息历史记录服务
 *
 * docs : http://www.rongcloud.cn/docs/server.html#history_message
 * @author RongCloud
 *
 */
public class History {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "message/history";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public History(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 消息历史记录下载地址获取 方法消息历史记录下载地址获取方法。获取 APP 内指定某天某小时内的所有会话消息记录的下载地址。（目前支持二人会话、讨论组、群组、聊天室、客服、系统通知消息历史记录下载）
     *
     * @param  date:指定北京时间某天某小时，格式为2014010101,表示：2014年1月1日凌晨1点。（必传）
     *
     * @return HistoryMessageResult
     * @throws Exception
     **/
    public HistoryMessageResult get(String date) throws Exception {
        if (date == null) {
            throw new ParamException("Paramer 'date' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&date=").append(URLEncoder.encode(date.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/history.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (HistoryMessageResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GET,HttpUtil.returnResult(conn)), HistoryMessageResult.class);
    }

    /**
     * 消息历史记录删除方法（删除 APP 内指定某天某小时内的所有会话消息记录。调用该接口返回成功后，date参数指定的某小时的消息记录文件将在随后的5-10分钟内被永久删除。）
     *
     * @param  date:指定北京时间某天某小时，格式为2014010101,表示：2014年1月1日凌晨1点。（必传）
     *
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult remove(String date) throws Exception {
        if (date == null) {
            throw new ParamException("Paramer 'date' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&date=").append(URLEncoder.encode(date.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/history/delete.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn)), ResponseResult.class);
    }
}
