package io.rong;

import io.rong.models.SMSImageCodeReslut;
import io.rong.models.TokenReslut;

import java.io.Reader;

/**
 * 使用代理示例
 * Created by Benz on 2017/3/9.
 */
public class ExampleForProxy {
    private static final String JSONFILE = Example.class.getClassLoader().getResource("jsonsource").getPath() + "/";

    public static void main(String[] args) throws Exception {
        String appKey = "appkey";//替换成您的appkey
        String appSecret = "secret";//替换成匹配上面key的secret

        Reader reader = null;
        RongCloud rongCloudProxy = RongCloud.getInstance(appKey, appSecret, "182.119.11.10", 50);

        System.out.println("************************User********************");
        // 获取 Token 方法
        TokenReslut userGetTokenResultProxy = rongCloudProxy.user.getToken("userId1", "username", "http://www.rongcloud.cn/images/logo.png");
        System.out.println("getToken:  " + userGetTokenResultProxy.toString());


        System.out.println("************************SMS********************");
        // 获取图片验证码方法
        SMSImageCodeReslut sMSGetImageCodeResult = rongCloudProxy.sms.getImageCode("app-key");
        System.out.println("getImageCode:  " + sMSGetImageCodeResult.toString());

    }
}
