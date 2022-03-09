package io.rong.methods.ultragroup;

import io.rong.RongCloud;
import io.rong.methods.ultragroup.ban.Ban;
import io.rong.methods.ultragroup.ban.User;
import io.rong.methods.ultragroup.ban.WhiteList;
import io.rong.methods.ultragroup.channel.BusChannel;
import io.rong.methods.ultragroup.expansion.Expansion;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.response.ResponseResult;
import io.rong.models.ultragroup.UltraGroupModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class UltraGroup {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "ultragroup";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;
    public Ban ban;
    public User user;
    public WhiteList whiteList;
    public BusChannel busChannel;
    public Expansion expansion;

    public UltraGroup(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
        this.user = new User(appKey, appSecret, rongCloud);
        this.whiteList = new WhiteList(appKey, appSecret, rongCloud);
        this.ban = new Ban(appKey, appSecret, rongCloud);
        this.busChannel = new BusChannel(appKey, appSecret, rongCloud);
        this.expansion = new Expansion(appKey, appSecret, rongCloud);
    }

    public Result create(UltraGroupModel group) throws Exception {
        //需要校验的字段
        String message = CommonUtil.checkFiled(group,PATH, CheckMethod.CREATE);
        if(null != message){
            return (ResponseResult) GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();

        sb.append("&userId=").append(URLEncoder.encode(group.getUserId().toString(), UTF8));
        sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
        sb.append("&groupName=").append(URLEncoder.encode(group.getName().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/create.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.CREATE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     *  解散超级群
     * @param groupId
     * @return
     * @throws Exception
     */
    public Result dis(String groupId) throws Exception {
        String message = CommonUtil.checkParam("id",groupId,PATH,CheckMethod.DIS);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/dis.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.DIS,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     *  加入超级群
     * @param group
     * @return
     * @throws Exception
     */
    public Result join(UltraGroupModel group) throws Exception {
        String message = CommonUtil.checkFiled(group,PATH,CheckMethod.JOIN);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(group.getId(), UTF8));
        sb.append("&userId=").append(URLEncoder.encode(group.getUserId(), UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/join.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.JOIN,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     *  退出超级群
     * @param group
     * @return
     * @throws Exception
     */
    public Result quit(UltraGroupModel group) throws Exception {
        String message = CommonUtil.checkFiled(group,PATH,CheckMethod.QUIT);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(group.getId(), UTF8));
        sb.append("&userId=").append(URLEncoder.encode(group.getUserId(), UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/quit.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.QUIT,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     *  更新超级群
     * @param group
     * @return
     * @throws Exception
     */
    public Result refresh(UltraGroupModel group) throws Exception {
        String message = CommonUtil.checkFiled(group,PATH,CheckMethod.REFRESH);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
        sb.append("&groupName=").append(URLEncoder.encode(group.getName().toString(), UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/refresh.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REFRESH,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }
}
