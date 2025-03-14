package io.rong.example.user;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.user.chat.Ban;
import io.rong.models.Result;
import io.rong.models.response.BanListResult;
import io.rong.models.user.BanListModel;
import io.rong.models.user.BanModel;

/**
 * Demo class
 *
 * @author RongCloud
 */
public class BanExample {

    /**
     * Replace with your App Key
     */
    private static final String appKey = "";
    /**
     * Replace with your App Secret
     */
    private static final String appSecret = "";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);

        Ban ban = rongCloud.user.ban;

        /**
         * 
         * Set user mute
         */
        BanModel model = new BanModel()
                .setUserId(new String[]{"CHIQ1", "CHIQ2"})
                .setState(1)
                .setType("PERSON");

        Result result = ban.set(model);
        System.out.println("set:  " + result.toString());


        BanListModel blModel = new BanListModel()
                .setType("PERSON");
        /**
         *
         * 
         *
         * Query muted user list
         */
        BanListResult blResult = ban.getList(blModel);
        System.out.println("getList:  " + blResult.toString());


    }
}
