package io.rong.example.user;

import io.rong.RongCloud;
import io.rong.methods.user.blacklist.Blacklist;
import io.rong.models.Result;
import io.rong.models.response.BlackListResult;
import io.rong.models.user.UserModel;

/**
 * @author RongCloud
 */
public class BlackListExample {
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
    private static final String api = "http://api.cn.ronghub.com";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //自定义 api 地址方式
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        Blacklist BlackList = rongCloud.user.blackList;

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/user/black.html#add
         * 添加用户到黑名单方法
         */
        UserModel blackUser = new UserModel().setId("hdsjGB88");
        UserModel[] blacklist = {blackUser};
        UserModel user = new UserModel()
                .setId("hdsjGB89")
                .setBlacklist(blacklist);


        Result userAddBlacklistResult = (Result)BlackList.add(user);
        System.out.println("addBlacklist:  " + userAddBlacklistResult.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/user/black.html#getList
         * 获取某用户的黑名单列表方法
         */
        UserModel user2 = new UserModel().setId("hdsjGB89");

        BlackListResult result = BlackList.getList(user2);
        System.out.println("query blacklist:  " + result.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/user/black.html#remove
         * 从黑名单中移除用户方法
         */
        Result removeResult = BlackList.remove(user);
        System.out.println("remove blacklist:  " + removeResult.toString());

    }

}
