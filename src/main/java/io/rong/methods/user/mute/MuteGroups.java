package io.rong.methods.user.mute;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.GroupMuteMembersListResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Group Mute Service
 * Mute group members. If groupId is not specified, it applies to all groups.
 *
 * */
public class MuteGroups {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "group/ban/user";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public MuteGroups(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;

    }

    /**
     * Add global mute for groups
     *
     * @param group: Group information. id, munite, memberIds (required)
     *
     * @return Result
     **/
    public Result add(GroupModel group) throws Exception {
        String message = CommonUtil.checkFiled(group, PATH, CheckMethod.ADD);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        GroupMember[] members = group.getMembers();
        for (GroupMember member : members) {
            sb.append("&userId=").append(URLEncoder.encode(member.getId().toString(), UTF8));
        }
        //sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
        sb.append("&minute=").append(URLEncoder.encode(group.getMinute().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/user/gag/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.ADD, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * Query globally muted group members
     *
     * @return ListGagGroupUserResult
     **/
    public GroupMuteMembersListResult getList() throws Exception {
        StringBuilder sb = new StringBuilder();
        //sb.append("&groupId=").append(URLEncoder.encode(groupId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/user/gag/list.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (GroupMuteMembersListResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn, rongCloud.getConfig())), GroupMuteMembersListResult.class);
    }

    /**
     * Remove global group mute
     *
     * @param  group: Group (required)
     *
     * @return ResponseResult
     **/
    public Result remove(GroupModel group) throws Exception {
        String message = CommonUtil.checkFiled(group,PATH, CheckMethod.REMOVE);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();

        GroupMember[] members = group.getMembers();
        for(GroupMember member : members){
            sb.append("&userId=").append(URLEncoder.encode(member.getId().toString(), UTF8));
        }

        //sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/user/gag/rollback.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }
}
