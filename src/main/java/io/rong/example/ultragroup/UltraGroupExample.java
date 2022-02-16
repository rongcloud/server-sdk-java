package io.rong.example.ultragroup;

import io.rong.RongCloud;
import io.rong.methods.ultragroup.UltraGroup;
import io.rong.models.Result;
import io.rong.models.ultragroup.UltraGroupModel;

/**
 * 超级群组
 */
public class UltraGroupExample {

    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "c9kqb3rdkbb8j";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "uTNrkYskbNC";
    /**
     * 自定义api地址
     * */
    private static final String api = "http://api-ucqa.rongcloud.net";

    /**
     * 本地调用测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

//        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //自定义 api 地址方式
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, api);

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

    }
}
