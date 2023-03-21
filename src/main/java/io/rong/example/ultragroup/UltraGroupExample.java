package io.rong.example.ultragroup;

import io.rong.RongCloud;
import io.rong.methods.ultragroup.UltraGroup;
import io.rong.models.Result;
import io.rong.models.message.ExpansionModel;
import io.rong.models.response.ExpansionResult;
import io.rong.models.response.GroupUserQueryResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.ultragroup.UltraGroupMember;
import io.rong.models.ultragroup.UltraGroupModel;
import io.rong.models.user.UserIdListModel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
    private static final String api = "";

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
         */
        UltraGroupModel ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setUserId("test1")
                .setName("test1");
        Result groupCreateResult = (Result) ultraGroup.create(ultraGroupModel);
        System.out.println("ultragroup create result:  " + groupCreateResult.toString());


        /**
         * 更新超级群信息
         */
        ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setName("test1");
        groupCreateResult = ultraGroup.refresh(ultraGroupModel);
        System.out.println("ultragroup refresh result:  " + groupCreateResult.toString());

        /**
         * 解散超级群
         */
        groupCreateResult = ultraGroup.dis("test");
        System.out.println("ultragroup dis result:  " + groupCreateResult.toString());

        /**
         * 加入超级群
         */
        ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setUserId("testuser");
        groupCreateResult = ultraGroup.join(ultraGroupModel);
        System.out.println("ultragroup join result:  " + groupCreateResult.toString());

        ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setUserId("testuser");
        groupCreateResult = ultraGroup.inMember(ultraGroupModel);
        System.out.println("ultragroup in member result:  " + groupCreateResult.toString());

        /**
         * 退出超级群
         */
        groupCreateResult = ultraGroup.quit(ultraGroupModel);
        System.out.println("ultragroup quit result:  " + groupCreateResult.toString());

        /**
         * 设置超级群禁言状态
         */
        groupCreateResult = ultraGroup.ban.set("test1", false);
        System.out.println("ultragroup ban set result:  " + groupCreateResult.toString());

        /**
         * 查询超级群禁言状态
         */
        groupCreateResult = ultraGroup.ban.check("test1");
        System.out.println("ultragroup ban check result:  " + groupCreateResult.toString());

        /**
         * 添加禁言成员
         */
        UltraGroupMember[] members = {new UltraGroupMember().setId("test1"),new UltraGroupMember().setId("test2")};
        ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setMembers(members);
        groupCreateResult = ultraGroup.user.add(ultraGroupModel);
        System.out.println("ultragroup ban user add result:  " + groupCreateResult.toString());

        /**
         * 获取禁言成员
         */
        groupCreateResult = ultraGroup.user.get("test1");
        System.out.println("ultragroup ban user get result:  " + groupCreateResult.toString());

        /**
         * 移除禁言成员
         */
        groupCreateResult = ultraGroup.user.remove(ultraGroupModel);
        System.out.println("ultragroup ban user remove result:  " + groupCreateResult.toString());

        /**
         * 添加禁言白名单
         */
        groupCreateResult = ultraGroup.whiteList.add(ultraGroupModel);
        System.out.println("ultragroup ban whitelist add result:  " + groupCreateResult.toString());

        /**
         * 获取禁言白名单
         */
        groupCreateResult = ultraGroup.whiteList.get("test1");
        System.out.println("ultragroup ban whitelist get result:  " + groupCreateResult.toString());

        /**
         * 移除禁言白名单
         */
        groupCreateResult = ultraGroup.whiteList.remove(ultraGroupModel);
        System.out.println("ultragroup ban whitelist remove result:  " + groupCreateResult.toString());

        /**
         * 创建频道
         */

        ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setBusChannel("channel");
        groupCreateResult = ultraGroup.busChannel.add(ultraGroupModel);
        System.out.println("ultragroup busChannel add result:  " + groupCreateResult.toString());

        /**
         * 查询频道列表
         */
        groupCreateResult = ultraGroup.busChannel.getList("test1",1, 10);
        System.out.println("ultragroup busChannel get list result:  " + groupCreateResult.toString());

        /**
         * 删除频道
         * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         */
        groupCreateResult = ultraGroup.busChannel.remove(ultraGroupModel);
        System.out.println("ultragroup busChannel remove result:  " + groupCreateResult.toString());



        ExpansionModel msg = new ExpansionModel();
        msg.setMsgUID("BS45-NPH4-HV87-10LM");
        msg.setUserId("WNYZbMqpH");
        msg.setTargetId("tjw3zbMrU");
        HashMap<String, String> kv = new HashMap<String, String>();
        kv.put("type1", "1");
        kv.put("type2", "2");
        kv.put("type3", "3");
        kv.put("type4", "4");
        msg.setExtraKeyVal(kv);
        ResponseResult result = ultraGroup.expansion.set(msg);
        System.out.println("set expansion:  " + result.toString());

        Set eKey = new HashSet();
        eKey.add("type1");
        eKey.add("type2");
        msg.setExtraKey(eKey);
        result = ultraGroup.expansion.remove(msg);
        System.out.println("remove expansion:  " + result.toString());

        ExpansionResult eResult = (ExpansionResult) ultraGroup.expansion.getList("BS45-NPH4-HV87-10LM","groupid");
        System.out.println("getList expansion:  " + eResult.toString());

        Result res = ultraGroup.notdisturb.set("groupid", 1);
        System.out.println("notdisturb set:  " + res.toString());

        Result result1 = ultraGroup.notdisturb.get("groupid", "");
        System.out.println("notdisturb get:  " + result1.toString());

        ultraGroupModel.setType(1);
        Result change = ultraGroup.busChannel.change(ultraGroupModel);
        System.out.println("busChannel change:  " + change.toString());

        ultraGroupModel.setMembers(members);
        Result privateUserAdd = ultraGroup.busChannel.privateUserAdd(ultraGroupModel);
        System.out.println("busChannel privateUserAdd:  " + privateUserAdd.toString());

        UserIdListModel listModel = ultraGroup.busChannel.privateUserGet("groupId", "buschannel");
        System.out.println("busChannel privateUserGet:  " + listModel.toString());

        ultraGroupModel.setMembers(members);
        Result privateUserRemove = ultraGroup.busChannel.privateUserRemove(ultraGroupModel);
        System.out.println("busChannel privateUserRemove:  " + privateUserRemove.toString());
    }
}
