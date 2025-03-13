package io.rong.example.user;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.user.blockpushperiod.BlockPushPeriod;
import io.rong.models.Result;
import io.rong.models.response.ResponseResult;
import io.rong.models.user.BlockPushPeriodModel;
import io.rong.models.user.UserModel;

/**
 * User Do Not Disturb
 * @author RongCloud
 */
public class BlackPushPeriodExample {
    /**
     * Replace with your App Key
     * */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret
     * */
    private static final String appSecret = "appSecret";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
        // Custom API URL
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        BlockPushPeriod pushPeriod = rongCloud.user.blockPushPeriod;

        /**
         *
         * Add User Do Not Disturb Period
         */
        BlockPushPeriodModel periodModel = new BlockPushPeriodModel();
        periodModel.setPeriod(100);
        periodModel.setStartTime("23:59:59");
        periodModel.setId("test");
        periodModel.setLevel(1);


        Result userAddBlacklistResult = (Result) pushPeriod.add(periodModel);
        System.out.println("addBlackPushPeriod:  " + userAddBlacklistResult.toString());

        /**
         *
         * Get User Do Not Disturb Period
         */
        UserModel user2 = new UserModel().setId("test");

        ResponseResult list = pushPeriod.getList(user2);
        System.out.println("query BlackPushPeriod:  " + list.toString());

        /**
         *
         * Remove user's Do Not Disturb period
         */
        Result removeResult = pushPeriod.remove(user2);
        System.out.println("remove BlackPushPeriod:  " + removeResult.toString());

    }

}
