package io.rong.example.ultragroup;

import io.rong.RongCloud;
import io.rong.methods.ultragroup.UltraGroup;
import io.rong.models.Result;
import io.rong.models.ultragroup.UltraGroupMember;
import io.rong.models.ultragroup.UltraGroupModel;

/**
 * 超级群组
 */
public class UltraGroupExample {

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
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //自定义 api 地址方式
//        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, api);

        UltraGroup ultraGroup = rongCloud.ultraGroup;

        /**
         * 创建超级群
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/basic#create_ultragroup
         */
        UltraGroupModel ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setUserId("test1")
                .setName("test1");
        Result groupCreateResult = (Result) ultraGroup.create(ultraGroupModel);
        System.out.println("ultragroup create result:  " + groupCreateResult.toString());


        /**
         * 更新超级群信息
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/basic#refresh_ultragroup
         */
        ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setName("test1");
        groupCreateResult = ultraGroup.refresh(ultraGroupModel);
        System.out.println("ultragroup refresh result:  " + groupCreateResult.toString());

        /**
         * 解散超级群
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/basic#dismiss_ultragroup
         */
        groupCreateResult = ultraGroup.dis("test");
        System.out.println("ultragroup dis result:  " + groupCreateResult.toString());

        /**
         * 加入超级群
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/basic#join_ultragroup
         */
        ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setUserId("testuser");
        groupCreateResult = ultraGroup.join(ultraGroupModel);
        System.out.println("ultragroup join result:  " + groupCreateResult.toString());

        /**
         * 退出超级群
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/basic#quit_ularagroup
         */
        groupCreateResult = ultraGroup.quit(ultraGroupModel);
        System.out.println("ultragroup quit result:  " + groupCreateResult.toString());

        /**
         * 设置超级群禁言状态
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/groupblock#all_ban
         */
        groupCreateResult = ultraGroup.ban.set("test1", false);
        System.out.println("ultragroup ban set result:  " + groupCreateResult.toString());

        /**
         * 查询超级群禁言状态
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/groupblock#ban_get
         */
        groupCreateResult = ultraGroup.ban.check("test1");
        System.out.println("ultragroup ban check result:  " + groupCreateResult.toString());

        /**
         * 添加禁言成员
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/memeberblock#gag_add
         */
        UltraGroupMember[] members = {new UltraGroupMember().setId("test1"),new UltraGroupMember().setId("test2")};
        ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setMembers(members);
        groupCreateResult = ultraGroup.user.add(ultraGroupModel);
        System.out.println("ultragroup ban user add result:  " + groupCreateResult.toString());

        /**
         * 获取禁言成员
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/memeberblock#gag_list
         */
        groupCreateResult = ultraGroup.user.get("test1");
        System.out.println("ultragroup ban user get result:  " + groupCreateResult.toString());

        /**
         * 移除禁言成员
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/memeberblock#gag-del-api
         */
        groupCreateResult = ultraGroup.user.remove(ultraGroupModel);
        System.out.println("ultragroup ban user remove result:  " + groupCreateResult.toString());

        /**
         * 添加禁言白名单
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/groupblock#add_ban
         */
        groupCreateResult = ultraGroup.whiteList.add(ultraGroupModel);
        System.out.println("ultragroup ban whitelist add result:  " + groupCreateResult.toString());

        /**
         * 获取禁言白名单
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/groupblock#ban_get
         */
        groupCreateResult = ultraGroup.whiteList.get("test1");
        System.out.println("ultragroup ban whitelist get result:  " + groupCreateResult.toString());

        /**
         * 移除禁言白名单
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/groupblock#remove_ban
         */
        groupCreateResult = ultraGroup.whiteList.remove(ultraGroupModel);
        System.out.println("ultragroup ban whitelist remove result:  " + groupCreateResult.toString());

        /**
         * 创建频道
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/channel#create_channel
         */

        ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setBusChannel("channel");
        groupCreateResult = ultraGroup.busChannel.add(ultraGroupModel);
        System.out.println("ultragroup busChannel add result:  " + groupCreateResult.toString());

        /**
         * 查询频道列表
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/channel#get_channel
         */
        groupCreateResult = ultraGroup.busChannel.getList("test1",1, 10);
        System.out.println("ultragroup busChannel get list result:  " + groupCreateResult.toString());

        /**
         * 删除频道
         * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/channel#delete_channel
         */
        groupCreateResult = ultraGroup.busChannel.remove(ultraGroupModel);
        System.out.println("ultragroup busChannel remove result:  " + groupCreateResult.toString());
    }
}
