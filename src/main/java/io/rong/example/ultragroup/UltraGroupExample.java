package io.rong.example.ultragroup;

import io.rong.RongCloud;
import io.rong.methods.group.Group;
import io.rong.methods.ultragroup.UltraGroup;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.group.UserGroup;
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
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        UltraGroup ultraGroup = rongCloud.ultraGroup;

        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/group/group.html#create
         *
         * 创建群组方法
         *
         */


        UltraGroupModel ultraGroupModel = new UltraGroupModel()
                .setId("groupId")
                .setUserId("test")
                .setName("testName");
        Result groupCreateResult = (Result) ultraGroup.create(ultraGroupModel);
        System.out.println("ultragroup create result:  " + groupCreateResult.toString());



    }
}
