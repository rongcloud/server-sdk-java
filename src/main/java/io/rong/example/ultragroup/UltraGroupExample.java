package io.rong.example.ultragroup;

import io.rong.RongCloud;
import io.rong.methods.ultragroup.UltraGroup;
import io.rong.models.Result;
import io.rong.models.ultragroup.UltraGroupMember;
import io.rong.models.ultragroup.UltraGroupModel;

import java.util.ArrayList;
import java.util.List;

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

        UltraGroupModel ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setUserId("test1")
                .setName("test1");
        Result groupCreateResult = (Result) ultraGroup.create(ultraGroupModel);
        System.out.println("ultragroup create result:  " + groupCreateResult.toString());


        ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setName("test1");
        groupCreateResult = ultraGroup.refresh(ultraGroupModel);
        System.out.println("ultragroup refresh result:  " + groupCreateResult.toString());

        groupCreateResult = ultraGroup.dis("test");
        System.out.println("ultragroup dis result:  " + groupCreateResult.toString());

        ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setUserId("testuser");
        groupCreateResult = ultraGroup.join(ultraGroupModel);
        System.out.println("ultragroup join result:  " + groupCreateResult.toString());

        groupCreateResult = ultraGroup.quit(ultraGroupModel);
        System.out.println("ultragroup quit result:  " + groupCreateResult.toString());

        groupCreateResult = ultraGroup.ban.set("test1", false);
        System.out.println("ultragroup ban set result:  " + groupCreateResult.toString());

        groupCreateResult = ultraGroup.ban.check("test1");
        System.out.println("ultragroup ban check result:  " + groupCreateResult.toString());

        String[] groupIds = {"test1", "test2"};
        groupCreateResult = ultraGroup.ban.remove(groupIds);
        System.out.println("ultragroup ban remove result:  " + groupCreateResult.toString());

        UltraGroupMember[] members = {new UltraGroupMember().setId("test1"),new UltraGroupMember().setId("test2")};
        ultraGroupModel = new UltraGroupModel()
                .setId("test1")
                .setMembers(members);
        groupCreateResult = ultraGroup.user.add(ultraGroupModel);
        System.out.println("ultragroup ban user add result:  " + groupCreateResult.toString());

        groupCreateResult = ultraGroup.user.get("test1");
        System.out.println("ultragroup ban user get result:  " + groupCreateResult.toString());

        groupCreateResult = ultraGroup.user.remove(ultraGroupModel);
        System.out.println("ultragroup ban user remove result:  " + groupCreateResult.toString());


        groupCreateResult = ultraGroup.whiteList.add(ultraGroupModel);
        System.out.println("ultragroup ban whitelist add result:  " + groupCreateResult.toString());

        groupCreateResult = ultraGroup.whiteList.get("test1");
        System.out.println("ultragroup ban whitelist get result:  " + groupCreateResult.toString());

        groupCreateResult = ultraGroup.whiteList.remove(ultraGroupModel);
        System.out.println("ultragroup ban whitelist remove result:  " + groupCreateResult.toString());

    }
}
