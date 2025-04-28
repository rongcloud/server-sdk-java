package io.rong.methods.group;

import io.rong.RongCloud;
import io.rong.methods.group.ban.Ban;
import io.rong.methods.group.gag.Gag;
import io.rong.methods.group.mute.MuteAllMembers;
import io.rong.methods.group.mute.MuteMembers;
import io.rong.methods.group.mute.whitelist.MuteWhiteList;
import io.rong.methods.group.remark.Remark;
import io.rong.models.*;
import io.rong.models.group.*;
import io.rong.models.response.GroupUserQueryResult;
import io.rong.models.response.OperationGroupResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 * Group Service
 */
public class Group {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "group";
    private String appKey;
    private String appSecret;
    public Gag gag;
    public Ban ban;
    public MuteAllMembers muteAllMembers;
    public MuteMembers muteMembers;
    public MuteWhiteList muteWhiteList;
    public Remark remark;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public Group(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
        this.gag = new Gag(appKey, appSecret, rongCloud);
        this.ban = new Ban(appKey, appSecret, rongCloud);
        this.muteAllMembers = new MuteAllMembers(appKey, appSecret, rongCloud);
        this.muteMembers = new MuteMembers(appKey, appSecret, rongCloud);
        this.muteWhiteList = new MuteWhiteList(appKey, appSecret, rongCloud);
        this.remark = new Remark(appKey, appSecret, rongCloud);


    }

    /**
     * Method to create a group (Creates a group and adds users to it. Users will be able to receive messages from this group. A single user can join up to 500 groups, and each group can have a maximum of 3000 members. There is no limit to the number of groups within the App. Note: This method is essentially an alias for the /group/join method.)
     *
     * url /group/create.json
     *
     * @param group The group information for creating the group
     *
     * @return Result
     **/
    public OperationGroupResult create(GroupModel group) throws Exception {
        String message = CommonUtil.checkFiled(group, PATH, CheckMethod.CREATE);
        if (null != message) {
            return (OperationGroupResult) GsonUtil.fromJson(message, OperationGroupResult.class);
        }
        StringBuilder sb = new StringBuilder();

		GroupMember[] members = group.getMembers();
		for(GroupMember member : members){
			sb.append("&userId=").append(URLEncoder.encode(member.getId().toString(), UTF8));
		}

	    sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
	    sb.append("&groupName=").append(URLEncoder.encode(group.getName().toString(), UTF8));
		sb = operateGroup(sb, group);
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/create.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
		return (OperationGroupResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.CREATE,HttpUtil.returnResult(conn, rongCloud.getConfig())), OperationGroupResult.class);
	}

    /**
     * Method to synchronize user groups (When connecting to the RongCloud server for the first time, it is necessary to submit all groups that the user corresponding to the userId has currently joined. This interface mainly prevents inconsistencies between the user group information in the application and the known user group information in RongCloud.)
     *
     * @param user: User group information
     *
     * @return ResponseResult
     **/
    public Result sync(UserGroup user) throws Exception {

		String message = CommonUtil.checkFiled(user,PATH,CheckMethod.SYNC);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
		}
	   	StringBuilder sb = new StringBuilder();
		sb.append("&userId=").append(URLEncoder.encode(user.getId(), UTF8));

		for (int i = 0 ; i< user.getGroups().length; i++) {
			GroupModel child  = user.getGroups()[i];
			if (child.getName() == null) {
				return new ResponseResult(1002,"Paramer 'group.name' is required");

			}
			if (child.getId() == null) {
				return new ResponseResult(1002,"Paramer 'group.id' is required");
			}
			sb.append("&group["+child.getId()+"]=").append(URLEncoder.encode(child.getName(), UTF8));
		}

	   	String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}

	   	HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/sync.json", "application/x-www-form-urlencoded");
	   	HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.SYNC,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
	}

    /**
     * Refresh group information method
     *
     * @param  group: Group information. id, name (required)
     *
     * @return ResponseResult
     **/
    public OperationGroupResult update(GroupModel group) throws Exception {
        String message = CommonUtil.checkFiled(group, PATH, CheckMethod.UPDATE);
        if (null != message) {
            return (OperationGroupResult) GsonUtil.fromJson(message, OperationGroupResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
        sb.append("&groupName=").append(URLEncoder.encode(group.getName().toString(), UTF8));
        sb = operateGroup(sb, group);
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/refresh.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        return (OperationGroupResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.UPDATE, HttpUtil.returnResult(conn, rongCloud.getConfig())), OperationGroupResult.class);
    }

    /**
     * Invites users to join a specified group. After joining, users can receive messages from the group. A single user can join up to 500 groups, and each group can have a maximum of 3000 members.
     *
     * @param group Parameters for users joining the specified group
     *
     * @return Result
     **/
    public Result invite(GroupModel group) throws Exception {
        String message = CommonUtil.checkFiled(group, PATH, CheckMethod.INVITE);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();

        GroupMember[] members = group.getMembers();
        for (GroupMember member : members) {
            sb.append("&userId=").append(URLEncoder.encode(member.getId().toString(), UTF8));
        }

        sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
        sb.append("&groupName=").append(URLEncoder.encode(group.getName().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/join.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.INVITE, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * Adds users to a specified group. After joining, users can receive messages from the group. A single user can join up to 500 groups, and each group can have a maximum of 3000 members.
     *
     * @param group Parameters for users joining the specified group
     *
     * @return Result
     **/
    public OperationGroupResult join(GroupModel group) throws Exception {
        String message = CommonUtil.checkFiled(group, PATH, CheckMethod.JOIN);
        if (null != message) {
            return (OperationGroupResult) GsonUtil.fromJson(message, OperationGroupResult.class);
        }

        StringBuilder sb = new StringBuilder();

        GroupMember[] members = group.getMembers();
        for (GroupMember member : members) {
            sb.append("&userId=").append(URLEncoder.encode(member.getId().toString(), UTF8));
        }

        sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
        sb.append("&groupName=").append(URLEncoder.encode(group.getName().toString(), UTF8));
        sb = operateGroup(sb, group);
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/join.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (OperationGroupResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.JOIN, HttpUtil.returnResult(conn, rongCloud.getConfig())), OperationGroupResult.class);
    }

    /**
     * Query group information
     *
     * @param  group: Group ID. (Required)
     *
     * @return GroupUserQueryResult
     **/
    public GroupUserQueryResult get(GroupModel group) throws Exception {

        String errMsg = CommonUtil.checkFiled(group, PATH, CheckMethod.GET);
        if (null != errMsg) {
            return (GroupUserQueryResult) GsonUtil.fromJson(errMsg, GroupUserQueryResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/user/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (GroupUserQueryResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GET, HttpUtil.returnResult(conn, rongCloud.getConfig())), GroupUserQueryResult.class);
    }

    /**
     * Quits a group (removes the user from the group and stops receiving messages from the group).
     *
     * @param  group: group.id, memberIds (required)
     *
     * @return ResponseResult
     **/
    public OperationGroupResult quit(GroupModel group) throws Exception {

		String message = CommonUtil.checkFiled(group,PATH,CheckMethod.QUIT);
		if(null != message){
			return (OperationGroupResult)GsonUtil.fromJson(message, OperationGroupResult.class);
		}
	    StringBuilder sb = new StringBuilder();

		GroupMember[] members = group.getMembers();
		for(GroupMember member : members){
			sb.append("&userId=").append(URLEncoder.encode(member.getId().toString(), UTF8));
		}

	    sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
		sb = operateGroup(sb, group);
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/quit.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

	    return (OperationGroupResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.QUIT,HttpUtil.returnResult(conn, rongCloud.getConfig())), OperationGroupResult.class);
	}

    /**
     * Disbands a group (disbands the group, and all users will no longer receive messages from the group).
     *
     * @param  group: id, member (required)
     *
     * @return ResponseResult
     **/
    public OperationGroupResult dismiss(GroupModel group) throws Exception {
        String message = CommonUtil.checkFiled(group, PATH, CheckMethod.DISMISS);
        if (null != message) {
            return (OperationGroupResult) GsonUtil.fromJson(message, OperationGroupResult.class);
        }

	    StringBuilder sb = new StringBuilder();
		GroupMember member = group.getMembers()[0];
		sb.append("&userId=").append(URLEncoder.encode(member.getId().toString(), UTF8));
	    sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
		sb = operateGroup(sb, group);
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/dismiss.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

	    return (OperationGroupResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.DISMISS,HttpUtil.returnResult(conn, rongCloud.getConfig())), OperationGroupResult.class);
	}

    /**
     * Group atomic operation
     * @return
     */
    private StringBuilder operateGroup(StringBuilder sb, GroupModel group) {
        if (group.isBindNotifyMsg()) {
            sb.append("&bindNotifyMsg=").append(group.isBindNotifyMsg());
            sb.append("&maxMember=").append(group.getMaxMember());
            if (StringUtils.isNotBlank(group.getFromUserId())) sb.append("&fromUserId=").append(group.getFromUserId());
            if (StringUtils.isNotBlank(group.getObjectName())) sb.append("&objectName=").append(group.getObjectName());
            if (StringUtils.isNotBlank(group.getContent())) sb.append("&content=").append(group.getContent());
            if (StringUtils.isNotBlank(group.getPushContent()))
                sb.append("&pushContent=").append(group.getPushContent());
            if (StringUtils.isNotBlank(group.getPushData())) sb.append("&pushData=").append(group.getPushData());
            sb.append("&isIncludeSender=").append(group.getIsIncludeSender());
            sb.append("&isPersisted=").append(group.getIsPersisted());
            if (StringUtils.isNotBlank(group.getPushExt())) sb.append("&pushExt=").append(group.getPushExt());
        }
        return sb;
    }


}