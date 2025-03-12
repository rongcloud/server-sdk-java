package io.rong.example.group.mute;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.group.mute.MuteMembers;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.GroupMuteMembersListResult;
/**
 *
 * 群组成员禁言例子
 * @author RongCloud
 *
 * @version 3.0.0
 */
public class MuteMembersExample {
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "appSecret";
    /**
     * 本地调用测试
     *
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //自定义 api 地址方式
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);

        MuteMembers muteMembers = rongCloud.group.muteMembers;
        /**
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 添加禁言群成员方法
         */

        GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

        GroupModel group = new GroupModel()
                .setId("groupId")
                .setMembers(members)
                .setMinute(5);
        Result result = muteMembers.add(group);
        System.out.println("group.muteMembers.add:  " + result.toString());

        /**
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 查询被禁言群成员
         */
        GroupMuteMembersListResult muteMembersResult = muteMembers.getList("groupId");
        System.out.println("group.muteMembers.getList:  " + muteMembersResult.toString());

        /**
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 移除禁言群成员
         */
        group = new GroupModel()
                .setId("groupId")
                .setMembers(members);

        Result groupRollBackGagUserResult = muteMembers.remove(group);
        System.out.println("group.muteMembers.remove:  " + groupRollBackGagUserResult.toString());

    }
}
