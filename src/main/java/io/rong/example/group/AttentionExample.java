package io.rong.example.group;

import io.rong.RongCloud;
import io.rong.models.Result;
import io.rong.models.group.AttentionModel;
import io.rong.models.response.group.AttentionResult;

/**
 * 特别关注群成员设置示例
 *
 * @author RongCloud
 */
public class AttentionExample {

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

        AttentionModel model = new AttentionModel();
        model.setUserId("user1");
        model.setGroupId("group1");
        model.setAttentionUserId(new String[]{"user2"});
        Result result = rongCloud.group.attention.set(model);
        System.out.println("attention set: " + result.toString());

        AttentionResult result2 = (AttentionResult) rongCloud.group.attention.query(model);
        System.out.println("attention query: " + result2.toString());

        result = rongCloud.group.attention.del(model);
        System.out.println("attention del: " + result.toString());

        result2 = (AttentionResult) rongCloud.group.attention.query(model);
        System.out.println("attention query: " + result2.toString());

    }
}
