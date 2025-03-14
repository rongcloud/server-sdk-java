package io.rong.methods.sensitive;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.models.CheckMethod;
import io.rong.models.response.ListWordfilterResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.CommonConstrants;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 *
 *  Sensitive Word Service
 *
 * @version
 * */
public class Wordfilter {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "sensitiveword";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public Wordfilter(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }


    /**
     * Add Sensitive Word Method (After setting sensitive words, users in the App will not receive messages containing sensitive words. By default, up to 50 sensitive words can be set.)
     *
     * @param  word: Sensitive word, maximum length of 32 characters. (Required)
     *
     * @return ResponseResult
     **/
    public ResponseResult add(String word) throws Exception {


        String message = CommonUtil.checkParam("keyword", word, PATH, CheckMethod.ADD);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&word=").append(URLEncoder.encode(word.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/wordfilter/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.ADD, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * Method to query the list of sensitive words
     *
     * @return ListWordfilterResult
     **/
    public ListWordfilterResult getList() throws Exception {
        StringBuilder sb = new StringBuilder();
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/wordfilter/list.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ListWordfilterResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GETLIST, HttpUtil.returnResult(conn, rongCloud.getConfig())), ListWordfilterResult.class);
    }

    /**
     * Method to remove a sensitive word from the sensitive word list
     *
     * @param word: Sensitive word, maximum length of 32 characters. (Required)
     *
     * @return ResponseResult
     **/
    public ResponseResult remove(String word) throws Exception {
        String message = CommonUtil.checkParam("keyword", word, PATH, CheckMethod.REMOVE);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }


        StringBuilder sb = new StringBuilder();
        sb.append("&word=").append(URLEncoder.encode(word.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/wordfilter/delete.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.REMOVE, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }


}