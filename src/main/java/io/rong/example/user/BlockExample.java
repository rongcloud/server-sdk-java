package io.rong.example.user;

import io.rong.RongCloud;
import io.rong.methods.user.block.Block;
import io.rong.models.Result;
import io.rong.models.response.BlockUserResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.user.UserModel;

public class BlockExample {
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
    private static final String api = "http://api.rong-api.com";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //自定义 api 地址方式
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        Block Block = rongCloud.user.block;

        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 解除用户封禁
         *
         */
        UserModel user = new UserModel().setId("hkjo09h").setMinute(1000);

        Result addBlockResult = (ResponseResult)Block.add(user);
        System.out.println("userAddBlock:  " + addBlockResult.toString());

        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 解除用户封禁
         *
         */
        ResponseResult unBlockResult = (ResponseResult)Block.remove(user.id);
        System.out.println("unBlock:  " + unBlockResult.toString());

        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 获取被封禁用户
         *
         */
        BlockUserResult blockResult = (BlockUserResult)Block.getList();
        System.out.println("queryBlock:  " + blockResult.toString());

    }

}
