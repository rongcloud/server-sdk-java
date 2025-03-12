package io.rong.example.user;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.*;
import io.rong.models.response.*;
import io.rong.models.user.ExpireModel;
import io.rong.models.user.UserModel;

/**
 * Demo class
 *
 * @author RongCloud
 */
public class UserExample {

    /**
     * 此处替换成您的appKey
     */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     */
    private static final String appSecret = "appSecret";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
        // 自定义 api 地址方式
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);
        // 使用 百度 HTTPDNS 获取最快的 IP 地址进行连接
        // BaiduHttpDNSUtil.setHostTypeIp("account_id", "secret", rongCloud.getApiHostType());

        // 设置连接超时时间
        // rongCloud.getApiHostType().setConnectTimeout(10000);
        // 设置读取超时时间
        // rongCloud.getApiHostType().setReadTimeout(10000);
        // 获取备用域名List
        // List<HostType> hosttypes = rongCloud.getApiHostListBackUp();
        // 设置连接、读取超时时间
        // for (HostType hosttype : hosttypes) {
        //     hosttype.setConnectTimeout(10000);
        //     hosttype.setReadTimeout(10000);
        // }

        User User = rongCloud.user;

        /**
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * 注册用户，生成用户在融云的唯一身份标识 Token
         */
        UserModel user = new UserModel()
            .setId("userxxd2")
            .setName("username")
            .setPortrait("http://www.rongcloud.cn/images/logo.png");
        TokenResult result = User.register(user);
        System.out.println("getToken:  " + result.toString());

        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * 刷新用户信息方法
         */
        Result refreshResult = User.update(user);
        System.out.println("refresh:  " + refreshResult.toString());

        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * 查询用户信息方法
         */
        UserResult userResult = User.get(user);
        System.out.println("getUserInfo:  " + userResult.toString());

        /**
         * 用户注销
         */
        ResponseResult abandon = User.deactivate(user);
        System.out.println("user abandon:  " + abandon.toString());

        /**
         * 查询已注销用户
         */
        UserDeactivateResult abandonList = User.deactivateList(1, 20);
        System.out.println("user abandon list:  " + abandonList.toString());

        /**
         * 用户激活
         */
        ResponseResult active = User.activate(user);
        System.out.println("user activate set:  " + active.toString());

        /**
         * 重新激活用户 ID
         */
        UserModel user1 = new UserModel().setIds(new String[]{"CHIQ1", "CHIQ2"});
        ResponseResult reactivate = User.reactivate(user1);
        System.out.println("user reactivate set:  " + reactivate.toString());

        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * 查询用户所在群组
         */
        UserGroupQueryResult userGroupResult = User.getGroups(user);
        System.out.println("getGroups:  " + userGroupResult.toString());


        ExpireModel expireModel = new ExpireModel()
                .setUserId(new String[]{"CHIQ1", "CHIQ2"})
                .setTime(1623123911000L);
        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * Token 失效
         */
        refreshResult = User.expire(expireModel);
        System.out.println("expire:  " + refreshResult.toString());


    }
}
