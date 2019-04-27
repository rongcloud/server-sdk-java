package io.rong.example.group.ban.user;

import io.rong.RongCloud;
import io.rong.methods.group.Group;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.ListGagGroupUserResult;
/**
 *
 * 群组成员全部禁言例子
 * @author RongCloud
 *
 * @version 3.0.0
 */
public class UserExample {
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
    private static final String api = "http://api-cn.ronghub.com";

    /**
     * 本地调用测试
     *
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //自定义 api 地址方式
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        Group group = rongCloud.group;
        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/group/gag.html#add
         * 设置用户在加入的所有群组中都不能发送消息
         */

        GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

        GroupModel groupModel = new GroupModel()
                .setMembers(members)
                .setMinute(5);
        Result result = group.ban.user.add(groupModel);
        System.out.println("group.ban.add:  " + result.toString());

        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/group/gag.html#getList
         * 获取所有群组禁言用户列表
         */
        groupModel = new GroupModel()
                .setMembers(members);
        ListGagGroupUserResult GroupBanResult = (ListGagGroupUserResult) group.ban.user.getList();
        System.out.println("group.ban.getList:  " + GroupBanResult.toString());

        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/group/gag.html#remove
         * 移除用户在所有群组中的禁言设置
         */
        groupModel = new GroupModel()
                .setMembers(members);
        //Result groupRollBackGagUserResult =  group.ban.user.remove(groupModel);
        //System.out.println("group.ban.remove:  " + groupRollBackGagUserResult.toString());


    }
}
