package io.rong.example.user;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.user.block.Block;
import io.rong.models.Result;
import io.rong.models.response.BlockUserResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.user.UserModel;

public class BlockExample {
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

        Block Block = rongCloud.user.block;

        /**
         *
         * 
         * Unban a user
         *
         */
        UserModel user = new UserModel().setId("hkjo09h").setMinute(1000);

        Result addBlockResult = (ResponseResult)Block.add(user);
        System.out.println("userAddBlock:  " + addBlockResult.toString());

        /**
         *
         * 
         * Unban a user
         *
         */
        ResponseResult unBlockResult = (ResponseResult)Block.remove(user.id);
        System.out.println("unBlock:  " + unBlockResult.toString());

        /**
         *
         * 
         * Get blocked users list
         *
         */
        BlockUserResult blockResult = (BlockUserResult)Block.getList();
        System.out.println("queryBlock:  " + blockResult.toString());

    }

}
