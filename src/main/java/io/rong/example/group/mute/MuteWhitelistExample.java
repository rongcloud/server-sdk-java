package io.rong.example.group.mute;

import io.rong.RongCloud;
import io.rong.methods.group.mute.whitelist.MuteWhiteList;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.GroupBanWhitelistResult;
/**
 *
 * 群组禁言白名单例子
 * @author RongCloud
 *
 * @version 3.0
 */
public class MuteWhitelistExample {
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
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //自定义 api 地址方式
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        MuteWhiteList muteWhiteList = rongCloud.group.muteWhiteList;

        /**
         * API 文档:
         * 添加禁言白名单用户方法
         */

        GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

        GroupModel groupModel = new GroupModel()
                .setId("groupId")
                .setMembers(members);
        Result result = muteWhiteList.user.add(groupModel);
        System.out.println("group.muteWhiteList.add:  " + result.toString());

        /**
         * API 文档:
         * 查询禁言白名单用户方法
         */
        groupModel = new GroupModel()
                .setId("12");
        GroupBanWhitelistResult GroupBanResult = (GroupBanWhitelistResult) muteWhiteList.user.getList("groupId");
        System.out.println("group.muteWhiteList.getList:  " + GroupBanResult.toString());

        /**
         * API 文档:
         * 移除禁言白名单用户方法
         */
        groupModel = new GroupModel()
                .setMembers(members)
                .setId("groupId");
        Result groupRollBackGagUserResult =  muteWhiteList.user.remove(groupModel);
        System.out.println("group.muteWhiteList.remove:  " + groupRollBackGagUserResult.toString());


    }
}
