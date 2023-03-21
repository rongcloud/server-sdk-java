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
import io.rong.models.response.ResponseResult;
import io.rong.models.response.StatusResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 * 群组服务
 * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 *
 * */
public class Group {

	private static final String UTF8 = "UTF-8";
	private static final String PATH = "group";
	private String appKey;
	private String appSecret;
	public  Gag gag;
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
		this.gag = new Gag(appKey,appSecret,rongCloud);
		this.ban = new Ban(appKey,appSecret,rongCloud);
		this.muteAllMembers = new MuteAllMembers(appKey,appSecret,rongCloud);
		this.muteMembers = new MuteMembers(appKey,appSecret,rongCloud);
		this.muteWhiteList = new MuteWhiteList(appKey,appSecret,rongCloud);
		this.remark = new Remark(appKey,appSecret,rongCloud);


	}
	/**
	 * 创建群组方法（创建群组，并将用户加入该群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人，App 内的群组数量没有限制.注：其实本方法是加入群组方法 /group/join 的别名。） 
	 *
	 * url /group/create.json
	 * docs https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
	 *
	 * @param group 创建群组的群组信息
	 *
	 * @return Result
	 **/
	public Result create(GroupModel group) throws Exception {
		//需要校验的字段
		String message = CommonUtil.checkFiled(group,PATH,CheckMethod.CREATE);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
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
		return (GroupMemberCount) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.CREATE,HttpUtil.returnResult(conn, rongCloud.getConfig())), GroupMemberCount.class);
	}

	/**
	 * 同步用户所属群组方法（当第一次连接融云服务器时，需要向融云服务器提交 userId 对应的用户当前所加入的所有群组，此接口主要为防止应用中用户群信息同融云已知的用户所属群信息不同步。）
	 *
	 * @param  user:用户群组信息
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
	 * 刷新群组信息方法
	 *
	 * @param  group:群组信息。id,name（必传）
	 *
	 * @return ResponseResult
	 **/
	public Result update(GroupModel group) throws Exception {
		String message = CommonUtil.checkFiled(group,PATH,CheckMethod.UPDATE);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
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
	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.UPDATE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
	}
	/**
	 * 邀请用户加入指定群组，加入后可以接收该群消息，同一用户最多可加入 500 个群，每个群最大至 3000 人。
	 *
	 * @param group 用户加入指定群组参数
	 *
	 * @return Result
	 **/
	public Result invite(GroupModel group) throws Exception {
		String message = CommonUtil.checkFiled(group,PATH,CheckMethod.INVITE);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
		}

		StringBuilder sb = new StringBuilder();

		GroupMember[] members = group.getMembers();
		for(GroupMember member : members){
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

		return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.INVITE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
	}
	/**
	 * 将用户加入指定群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人。
	 *
	 * @param group 用户加入指定群组参数
	 *
	 * @return Result
	 **/
	public Result join(GroupModel group) throws Exception {
		String message = CommonUtil.checkFiled(group,PATH,CheckMethod.JOIN);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
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
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/join.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
	    
	    return (GroupMemberCount) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.JOIN,HttpUtil.returnResult(conn, rongCloud.getConfig())), GroupMemberCount.class);
	}
	
	/**
	 * 查询群信息
	 * 
	 * @param  group:群组.Id。（必传）
	 *
	 * @return GroupUserQueryResult
	 **/
	public GroupUserQueryResult get(GroupModel group) throws Exception {

		String errMsg = CommonUtil.checkFiled(group,PATH,CheckMethod.GET);
		if(null != errMsg){
			return (GroupUserQueryResult)GsonUtil.fromJson(errMsg,GroupUserQueryResult.class);
		}
	    StringBuilder sb = new StringBuilder();
	    sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/user/query.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
	    
	    return (GroupUserQueryResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GET,HttpUtil.returnResult(conn, rongCloud.getConfig())), GroupUserQueryResult.class);
	}
	
	/**
	 * 退出群组方法（将用户从群中移除，不再接收该群组的消息.） 
	 * 
	 * @param  group:群组.id, memberIds（必传）
	 *
	 * @return ResponseResult
	 **/
	public Result quit(GroupModel group) throws Exception {

		String message = CommonUtil.checkFiled(group,PATH,CheckMethod.QUIT);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
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

	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.QUIT,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
	}

	/**
	 * 解散群组方法。（将该群解散，所有用户都无法再接收该群的消息。） 
	 * 
	 * @param  group: id,member。（必传）
	 *
	 * @return ResponseResult
	 **/
	public Result dismiss(GroupModel group) throws Exception {
		String message = CommonUtil.checkFiled(group,PATH,CheckMethod.DISMISS);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
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
	    
	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.DISMISS,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
	}

	/**
	 * 群原子操作
	 * @return
	 */
	private StringBuilder operateGroup(StringBuilder sb, GroupModel group){
		if(group.isBindNotifyMsg()){
			sb.append("&bindNotifyMsg=").append(group.isBindNotifyMsg());
			sb.append("&maxMember=").append(group.getMaxMember());
			if(StringUtils.isNotBlank(group.getFromUserId())) sb.append("&fromUserId=").append(group.getFromUserId());
			if(StringUtils.isNotBlank(group.getObjectName())) sb.append("&objectName=").append(group.getObjectName());
			if(StringUtils.isNotBlank(group.getContent())) sb.append("&content=").append(group.getContent());
			if(StringUtils.isNotBlank(group.getPushContent())) sb.append("&pushContent=").append(group.getPushContent());
			if(StringUtils.isNotBlank(group.getPushData())) sb.append("&pushData=").append(group.getPushData());
			sb.append("&isIncludeSender=").append(group.getIsIncludeSender());
			sb.append("&isPersisted=").append(group.getIsPersisted());
			if(StringUtils.isNotBlank(group.getPushExt())) sb.append("&pushExt=").append(group.getPushExt());
		}
		return sb;
	}


}