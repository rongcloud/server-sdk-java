package io.rong.example.group.ban;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.group.ban.Ban;
import io.rong.models.Result;
import io.rong.models.response.GroupBanResult;

/**
 *
 * Example for group mute
 * @author RongCloud
 *
 * @version 3.0
 */
public class BanExample {
    /**
     * Replace with your appKey
     * */
    private static final String appKey = "appKey";
    /**
     * Replace with your appSecret
     * */
    private static final String appSecret = "appSecret";

    /**
     * Local test execution
     *
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
        //Custom API URL
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        Ban ban = rongCloud.group.ban;
        /**
         * API Documentation:
         * Add mute group method
         */

            String[] groupIds = {"ghJiu7H1","ghJiu7H2","ghJiu7H3","ghJiu7H4","ghJiu7H712","ghJiu7H6","ghJiu7H7","ghJiu7H8","ghJiu7H9","ghJiu7H10","ghJiu7H11","ghJiu7H12","ghJiu7H13","ghJiu7H14","ghJiu7H15","ghJiu7H16","ghJiu7H12","ghJiu7H18"};
            Result result = ban.add(groupIds);
            System.out.println("group.ban.add:  " + result.toString());

        /**
         * 
         * Query all muted groups method
         */
        GroupBanResult GroupBanResult = (GroupBanResult) ban.getList();
        System.out.println("group.ban.getList:  " + GroupBanResult.toString());

        /**
         * 
         * Method to check muted groups
         */
        GroupBanResult GroupBanCheckResult = (GroupBanResult) ban.check(groupIds);
        System.out.println("group.ban.check:  " + GroupBanCheckResult.toString());

        /**
         * 
         * Method to remove muted groups
         */
        Result groupRollBackGagUserResult = ban.remove(groupIds);
        System.out.println("group.ban.remove:  " + groupRollBackGagUserResult.toString());


        }
}
