package io.rong.methods.message.history;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.response.HistoryMessageResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
/**
 * 消息历史记录服务
 *
 * docs : https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
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
    public Result get(String date) throws Exception {
        if (date == null) {
            return new ResponseResult(1002,"Paramer 'date' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&date=").append(URLEncoder.encode(date.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/history.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (HistoryMessageResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GET,HttpUtil.returnResult(conn, rongCloud.getConfig())), HistoryMessageResult.class);
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
	 * 清除历史消息 -
	 * 如用户开通了单群聊消息云存储服务，可通过此接口按会话清除某用户指定时间之前服务端存储的历史消息，清除后用户在客户端无法再获取到存储到融云服务端历史消息，请谨慎执行此操作。
	 * 
	 * 参考文档: https://docs.rongcloud.cn/im/server/message_clean/
	 * 
	 * @param conversationType 会话类型，支持单聊、群聊、系统消息，单聊会话是 1、群组会话是 3、系统通知是 6 （必传)
	 * @param fromUserId 操作用户 ID，删除该用户指定会话 msgTimestamp 前的历史消息（必传）
	 * @param targetId 清除的目标会话 ID，（必传）
	 * @param msgTimestamp 清除该时间戳之前的所有历史消息，精确到毫秒，为空时清除该会话的所有历史消息。（非必传）
	 * 
	 * @return ResponseResult
	 * @throws Exception
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
}
