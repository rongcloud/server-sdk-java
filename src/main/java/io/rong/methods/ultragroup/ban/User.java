package io.rong.methods.ultragroup.ban;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.response.GroupUserQueryResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.ultragroup.UltraGroupMember;
import io.rong.models.ultragroup.UltraGroupModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Ultra Group User Mute
 *
 */
public class User {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "ultragroup/ban/user";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public User(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;

    }

    /**
     * Add Ultra Group User Mute
     *
     * @param group: Ultra group. id, memberIds (required)
     *
     * @return Result
     **/
    public Result add(UltraGroupModel group) throws Exception {
        String message = CommonUtil.checkFiled(group, PATH, CheckMethod.ADD);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        UltraGroupMember[] members = group.getMembers();
        for (UltraGroupMember member : members) {
            sb.append("&userIds=").append(URLEncoder.encode(member.getId().toString(), UTF8));
        }
        sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
        if (group.getBusChannel() != null)
            sb.append("&busChannel=").append(URLEncoder.encode(group.getBusChannel().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/userbanned/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.ADD, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * Query the method to get the banned users in an ultra group
     *
     * @return GroupUserQueryResult
     **/
    public Result get(String groupId) throws Exception {
        return get(groupId, "");
    }
    public Result get(String groupId, String busChannel) throws Exception {
        String message = CommonUtil.checkParam("id", groupId, PATH, CheckMethod.GET);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
        sb.append("&busChannel=").append(URLEncoder.encode(busChannel, UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/userbanned/get.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (GroupUserQueryResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GET, HttpUtil.returnResult(conn, rongCloud.getConfig())), GroupUserQueryResult.class);
    }

    /**
     * Remove the ban for a user in an ultra group
     *
     * @param  group: Ultra group (required)
     * @return ResponseResult
     **/
    public Result remove(UltraGroupModel group) throws Exception {
        // Parameter validation
        String message = CommonUtil.checkFiled(group, PATH, CheckMethod.DEL);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();

        UltraGroupMember[] members = group.getMembers();
        for(UltraGroupMember member : members){
            sb.append("&userIds=").append(URLEncoder.encode(member.getId().toString(), UTF8));
        }

        sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
        if(group.getBusChannel() != null) sb.append("&busChannel=").append(URLEncoder.encode(group.getBusChannel().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/userbanned/del.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.DEL,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }
}

