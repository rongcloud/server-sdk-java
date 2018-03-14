package io.rong.example.user;

import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.*;
import io.rong.models.response.*;
import io.rong.models.user.UserModel;

/**
 * Demo class
 *
 * @author hc
 * @date 2017/12/30
 */
public class UserExample {
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "appSecret";

    private static final String api = "http://192.168.155.13:9200";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);
        User User = rongCloud.user;

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/user/user.html#register
         *
         * 注册用户，生成用户在融云的唯一身份标识 Token
         */
        String appKey = "e0x9wycfx7flq";
        String appSecret = "STCevzDS6Xy18n";
        UserModel user = new UserModel()
                .setId("userxxd1")
                .setName("username")
                .setPortrait("http://www.rongcloud.cn/images/logo.png");
        TokenResult result = User.register(user);
        System.out.println("getToken:  " + result.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/user/user.html#refresh
         *
         * 刷新用户信息方法
         */
        Result refreshResult = User.update(user);
        System.out.println("refresh:  " + refreshResult.toString());

    }
}
