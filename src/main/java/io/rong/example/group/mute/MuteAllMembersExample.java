package io.rong.example.group.mute;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.group.mute.MuteAllMembers;
import io.rong.models.Result;
import io.rong.models.response.GroupMuteAllMembersCheckResult;
import io.rong.models.response.GroupMuteAllMembersListResult;

/**
 *
 * 群组禁言例子
 * @author RongCloud
 *
 * @version 3.0
 */
public class MuteAllMembersExample {
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

        MuteAllMembers muteAllMembers = rongCloud.group.muteAllMembers;
        /**
         * API 文档:
         * 添加禁言群方法
         */
        String[] groupIds = {"ghJiu7H1","ghJiu7H2","ghJiu7H3","ghJiu7H4","ghJiu7H712","ghJiu7H6","ghJiu7H7","ghJiu7H8","ghJiu7H9","ghJiu7H10","ghJiu7H11","ghJiu7H12","ghJiu7H13","ghJiu7H14","ghJiu7H15","ghJiu7H16","ghJiu7H12","ghJiu7H18"};
        Result result = muteAllMembers.add(groupIds);
        System.out.println("group.muteAllMembers.add:  " + result.toString());

        /**
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 查询所有设置禁言群方法
         */
        GroupMuteAllMembersListResult GroupMuteAllMembersResult = (GroupMuteAllMembersListResult)muteAllMembers.getList();
        System.out.println("group.muteAllMembers.getList:  " + GroupMuteAllMembersResult.toString());

        /**
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 检查禁言群方法
         */
        GroupMuteAllMembersCheckResult GroupBanCheckResult = (GroupMuteAllMembersCheckResult)muteAllMembers.check(groupIds);
        System.out.println("group.muteAllMembers.check:  " + GroupBanCheckResult.toString());

        /**s
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 移除禁言群方法
         */
        Result groupMuteAllMembersResult = muteAllMembers.remove(groupIds);
        System.out.println("group.muteAllMembers.remove:  " + groupMuteAllMembersResult.toString());


    }
}
