package io.rong.methods.ultragroup.notdisturb;

import io.rong.RongCloud;
import io.rong.methods.ultragroup.ban.User;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.response.NotdisturbStatusResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Notdisturb {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "ultragroup/notdisturb";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;
    public User user;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Notdisturb(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
        this.user = new User(appKey,appSecret,rongCloud);

    }
    /**
     * 设置超级群免打扰级别
     *
     * @param groupId:超级群 ID
     * @param unpushLevel:免打扰级别
     *
     * @return Result
     **/
    public Result set(String groupId, int unpushLevel) throws Exception {
        return set(groupId, unpushLevel, "");
    }
    public Result set(String groupId, int unpushLevel, String busChannel) throws Exception {
        String message = CommonUtil.checkParam("id",groupId,PATH, CheckMethod.SET);
        if(null != message){
            return (ResponseResult) GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
        sb.append("&busChannel=").append(URLEncoder.encode(busChannel, UTF8));
        sb.append("&unpushLevel=").append(unpushLevel);
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/notdisturb/set.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.SET,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    public Result get(String groupId, String busChannel) throws Exception {
        String message = CommonUtil.checkParam("id",groupId,PATH, CheckMethod.SET);
        if(null != message){
            return (ResponseResult) GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
        sb.append("&busChannel=").append(URLEncoder.encode(busChannel, UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/notdisturb/get.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (NotdisturbStatusResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.SET,HttpUtil.returnResult(conn, rongCloud.getConfig())), NotdisturbStatusResult.class);
    }
}
