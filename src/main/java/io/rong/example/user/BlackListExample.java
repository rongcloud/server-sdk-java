package io.rong.example.user;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.user.blacklist.Blacklist;
import io.rong.models.Result;
import io.rong.models.response.BlackListResult;
import io.rong.models.response.PagingQueryBlacklistResult;
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

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
        //自定义 api 地址方式
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        Blacklist balackList = rongCloud.user.blackList;

        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 添加用户到黑名单方法
         */
        UserModel blackUser = new UserModel().setId("hdsjGB88");
        UserModel[] blacklist = {blackUser};
        UserModel user = new UserModel()
                .setId("hdsjGB89")
                .setBlacklist(blacklist);


        Result userAddBlacklistResult = balackList.add(user);
        System.out.println("addBlacklist:  " + userAddBlacklistResult.toString());

        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 获取某用户的黑名单列表方法
         */
        UserModel user2 = new UserModel().setId("hdsjGB89");

        BlackListResult result = balackList.getList(user2);
        System.out.println("query blacklist:  " + result.toString());


        /**
         *  分页查询用户黑名单
         */
        PagingQueryBlacklistResult pagingQueryBlacklistResult = balackList.pagingQueryBlacklist("BB_0", "", 20);
        System.out.println("pagingQueryBlacklist:  " + pagingQueryBlacklistResult.toString());

        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 从黑名单中移除用户方法
         */
        Result removeResult = balackList.remove(user);
        System.out.println("remove blacklist:  " + removeResult.toString());

    }

}
