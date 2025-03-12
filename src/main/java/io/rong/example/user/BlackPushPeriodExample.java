package io.rong.example.user;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.user.blockpushperiod.BlockPushPeriod;
import io.rong.models.Result;
import io.rong.models.response.ResponseResult;
import io.rong.models.user.BlockPushPeriodModel;
import io.rong.models.user.UserModel;

/**
 * 用户免打扰
 * @author RongCloud
 */
public class BlackPushPeriodExample {
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

        BlockPushPeriod pushPeriod = rongCloud.user.blockPushPeriod;

        /**
         *
         * 添加用户免打扰时间段
         */
        BlockPushPeriodModel periodModel = new BlockPushPeriodModel();
        periodModel.setPeriod(100);
        periodModel.setStartTime("23:59:59");
        periodModel.setId("test");
        periodModel.setLevel(1);


        Result userAddBlacklistResult = (Result)pushPeriod.add(periodModel);
        System.out.println("addBlackPushPeriod:  " + userAddBlacklistResult.toString());

        /**
         *
         * 获取用户免打扰时间段
         */
        UserModel user2 = new UserModel().setId("test");

        ResponseResult list = pushPeriod.getList(user2);
        System.out.println("query BlackPushPeriod:  " + list.toString());

        /**
         *
         * 移除用户免打扰时间段
         */
        Result removeResult = pushPeriod.remove(user2);
        System.out.println("remove BlackPushPeriod:  " + removeResult.toString());

    }

}
