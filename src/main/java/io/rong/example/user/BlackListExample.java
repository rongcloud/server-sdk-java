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
     * Replace with your App Key
     */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret
     */
    private static final String appSecret = "appSecret";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
        // Custom API URL
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        Blacklist balackList = rongCloud.user.blackList;

        /**
         *
         * 
         * Add a user to the blocklist
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
         * 
         * Get the blocklist of a user
         */
        UserModel user2 = new UserModel().setId("hdsjGB89");

        BlackListResult result = balackList.getList(user2);
        System.out.println("query blacklist:  " + result.toString());


        /**
         *  Paging query user blocklist
         */
        PagingQueryBlacklistResult pagingQueryBlacklistResult = balackList.pagingQueryBlacklist("BB_0", "", 20);
        System.out.println("pagingQueryBlacklist:  " + pagingQueryBlacklistResult.toString());

        /**
         *
         * 
         * Method to remove a user from the blocklist
         */
        Result removeResult = balackList.remove(user);
        System.out.println("remove blacklist:  " + removeResult.toString());

    }

}
