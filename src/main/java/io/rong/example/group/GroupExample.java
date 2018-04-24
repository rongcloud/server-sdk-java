package io.rong.example.group;

import io.rong.RongCloud;
import io.rong.methods.group.Group;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.group.UserGroup;
import io.rong.models.response.GroupUserQueryResult;

/**
 *
 * 群组服务示例
 * @author RongCloud
 *
 * @version 3.0.0
 */
public class GroupExample {
	/**
	 * 此处替换成您的appKey
	 * */
	private static final String appKey = "appKey";
	/**
	 * 此处替换成您的appSecret
	 * */
	private static final String appSecret = "appSecret";
	/**
	 * 自定义api地址
	 * */
	private static final String api = "http://api.cn.ronghub.com";

	/**
	 * 本地调用测试
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
		//自定义 api 地址方式
		// RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

		Group Group = rongCloud.group;

		/**
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/group/group.html#create
		 *
		 * 创建群组方法
		 *
		 */
		GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};


		GroupModel group = new GroupModel()
				.setId("groupId")
				.setMembers(members)
				.setName("groupName");
		Result groupCreateResult = (Result)Group.create(group);
		System.out.println("group create result:  " + groupCreateResult.toString());

		/**
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/group/group.html#sync
		 *
		 * 	同步用户所属群组方法
		 */

		GroupModel group1 = new GroupModel()
				.setId("groupId1")
				.setName("groupName1");
		GroupModel group2 = new GroupModel()
				.setId("groupId2")
				.setName("groupName2");
		GroupModel[] groups = {group1,group2};
		UserGroup user = new UserGroup()
				.setId("jhkoi90jj")
				.setGroups(groups);

		Result syncResult = (Result)Group.sync(user);
		System.out.println("group sync:  " + syncResult.toString());


		/**
		 *
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/group/group.html#refresh
		 *  刷新群组信息方法
		 */
		//GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

		group = new GroupModel()
				.setId("groupId")
				.setName("groupName");
		Result refreshResult = (Result)Group.update(group);
		System.out.println("refresh:  " + refreshResult.toString());

		/**
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/group/group.html#join
		 *
		 * 邀请用户加入群组
		 *
		 */
		group = new GroupModel()
				.setId("hdiuj87jj")
				.setMembers(members)
				.setName("groupName");
		Result groupInviteResult = (Result)rongCloud.group.invite(group);
		System.out.println("invite:  " + groupInviteResult.toString());

		/**
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/group/group.html#join
		 *
		 * 用户加入指定群组
		 *
		 */
		group = new GroupModel()
				.setId("groupId")
				.setMembers(members)
				.setName("groupName");
		Result groupJoinResult = (Result)Group.join(group);
		System.out.println("join:  " + groupJoinResult.toString());

		/**
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/group/group.html#getMembers
		 *
		 * 查询群成员方法
		 *
		 */
		group = new GroupModel().setId("groupId");
		GroupUserQueryResult getMemberesult = Group.get(group);
		System.out.println("group getMember:  " + getMemberesult.toString());

		/**
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/group/group.html#quit
		 *
		 * 退出群组
		 *
		 */
		group = new GroupModel()
				.setId("groupId")
				.setMembers(members)
				.setName("groupName");
		Result groupQuitResult = (Result)Group.quit(group);
		System.out.println("quit:  " + groupQuitResult.toString());

		/**
		 *
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/group/group.html#dismiss
		 *
		 * 解散群组
		 *
		 */
		members = new GroupMember[]{new GroupMember().setId("ghJiu7H1")};

		group = new GroupModel()
				.setId("groupId")
				.setMembers(members);
		Result groupDismissResult = (Result)Group.dismiss(group);
		System.out.println("dismiss:  " + groupDismissResult.toString());

	 }

}