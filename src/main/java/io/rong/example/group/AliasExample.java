package io.rong.example.group;

import io.rong.RongCloud;
import io.rong.models.Result;
import io.rong.models.group.AliasModel;
import io.rong.models.response.group.AliasResult;

/**
 * 用户群组名称备注名设置示例
 *
 * @author RongCloud
 */
public class AliasExample {

    /**
     * 此处替换成您的appKey
     */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     */
    private static final String appSecret = "appSecret";
    /**
     * 自定义api地址
     */
    private static final String api = "http://api.rong-api.com";


    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        AliasModel model = new AliasModel();
        model.setUserId("user1");
        model.setGroupId("group1");
        model.setRemarkName("name1");
        Result result = rongCloud.group.alias.set(model);
        System.out.println("alias set: " + result.toString());

        AliasResult result2 = (AliasResult) rongCloud.group.alias.query(model);
        System.out.println("alias query: " + result2.toString());

        result = rongCloud.group.alias.del(model);
        System.out.println("alias del: " + result.toString());

        result2 = (AliasResult) rongCloud.group.alias.query(model);
        System.out.println("alias query: " + result2.toString());

    }
}
