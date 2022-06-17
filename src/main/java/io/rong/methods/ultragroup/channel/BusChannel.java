package io.rong.methods.ultragroup.channel;

import io.rong.RongCloud;
import io.rong.methods.ultragroup.ban.User;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.response.*;
import io.rong.models.ultragroup.UltraGroupMember;
import io.rong.models.ultragroup.UltraGroupModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class BusChannel {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "ultragroup/channel";
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
    public BusChannel(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
        this.user = new User(appKey,appSecret,rongCloud);

    }
    /**
     * 增加超级群的busChannel
     *
     * @param group:超级群
     *
     * @return Result
     **/
    public Result add(UltraGroupModel group) throws Exception {
        String message = CommonUtil.checkFiled(group,PATH, CheckMethod.CREATE);
        if(null != message){
            return (ResponseResult) GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(group.getId(), UTF8));
        sb.append("&busChannel=").append(URLEncoder.encode(group.getBusChannel(), UTF8));
        sb.append("&type=").append(group.getType());
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/channel/create.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.CREATE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 查询超级群的busChannel
     *
     * @param  groupId:超级群Id。（必传）
     * @param  page:分页。
     * @param  size:分页条数。
     *
     * @return boolean
     **/
    public Result getList(String groupId,Integer page,Integer size) throws Exception {
        String message = CommonUtil.checkParam("id",groupId,PATH,CheckMethod.GET);
        if(null != message){
            return (Result)GsonUtil.fromJson(message, GroupBanWhitelistResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));
        if (size != null) {
            sb.append("&size=").append(URLEncoder.encode(size.toString(), UTF8));
        }
        if (page != null) {
            sb.append("&page=").append(URLEncoder.encode(page.toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/channel/get.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ChannelListResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GET,HttpUtil.returnResult(conn, rongCloud.getConfig())), ChannelListResult.class);
    }

    /**
     * 删除超级群的busChannel
     *
     * @param  group:超级群
     *
     * @return ResponseResult
     **/
    public Result remove(UltraGroupModel group) throws Exception {
        //参数校验
        String message = CommonUtil.checkFiled(group,PATH,CheckMethod.DEL);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(group.getId(), UTF8));
        sb.append("&busChannel=").append(URLEncoder.encode(group.getBusChannel(), UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/channel/del.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.DEL,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 超级群频道类型切换
     *
     * @param group:超级群
     *
     * @return Result
     **/
    public Result change(UltraGroupModel group) throws Exception {
        String message = CommonUtil.checkFiled(group,PATH, CheckMethod.CREATE);
        if(null != message){
            return (ResponseResult) GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(group.getId(), UTF8));
        sb.append("&busChannel=").append(URLEncoder.encode(group.getBusChannel(), UTF8));
        sb.append("&type=").append(group.getType());
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/channel/type/change.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.CREATE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 增加超级群私有频道白名单列表
     *
     * @param group:群组信息。id  , memberIds（必传）
     *
     * @return Result
     **/
    public Result privateUserAdd(UltraGroupModel group) throws Exception {
        String message = CommonUtil.checkFiled(group,PATH,CheckMethod.ADD);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        UltraGroupMember[] members = group.getMembers();
        for(UltraGroupMember member : members){
            sb.append("&userIds=").append(URLEncoder.encode(member.getId().toString(), UTF8));
        }
        sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
        sb.append("&busChannel=").append(URLEncoder.encode(group.getBusChannel().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/channel/private/users/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 查询超级群私有频道白名单列表方法
     *
     * @return GroupUserQueryResult
     **/
    public GroupUserQueryResult privateUserGet(String groupId, String busChannel) throws Exception {
        return privateUserGet(groupId, busChannel, 1, 200);
    }
    public GroupUserQueryResult privateUserGet(String groupId, String busChannel, int page, int pageSize) throws Exception {

        String errMsg = CommonUtil.checkParam("id", groupId,PATH,CheckMethod.GET);
        if(null != errMsg){
            return (GroupUserQueryResult)GsonUtil.fromJson(errMsg,GroupUserQueryResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
        sb.append("&busChannel=").append(URLEncoder.encode(busChannel, UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/channel/private/users/get.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (GroupUserQueryResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GET,HttpUtil.returnResult(conn, rongCloud.getConfig())), GroupUserQueryResult.class);
    }

    /**
     * 删除超级群禁言白名单列表方法
     *
     * @param  group:群组（必传）
     *
     * @return ResponseResult
     **/
    public Result privateUserRemove(UltraGroupModel group) throws Exception {
        //参数校验
        String message = CommonUtil.checkFiled(group,PATH, CheckMethod.ADD);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
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

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/channel/private/users/del.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.DEL,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }
}
