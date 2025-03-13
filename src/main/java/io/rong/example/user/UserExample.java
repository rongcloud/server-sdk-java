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
     * Replace with your App Key
     */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret
     */
    private static final String appSecret = "appSecret";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);

        User User = rongCloud.user;

        /**
         * Register a user and generate a unique identity Token for the user in RongCloud.
         */
        UserModel user = new UserModel()
            .setId("userxxd2")
            .setName("username")
            .setPortrait("http://www.rongcloud.cn/images/logo.png");
        TokenResult result = User.register(user);
        System.out.println("getToken:  " + result.toString());

/**
 * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 *
 * Method to refresh user information.
 */
        Result refreshResult = User.update(user);
        System.out.println("refresh:  " + refreshResult.toString());

        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * Method to query user information.
         */
        UserResult userResult = User.get(user);
        System.out.println("getUserInfo:  " + userResult.toString());

        /**
         * Deactivate a user.
         */
        ResponseResult abandon = User.deactivate(user);
        System.out.println("user abandon:  " + abandon.toString());

        /**
         * Query deactivated users.
         */
        UserDeactivateResult abandonList = User.deactivateList(1, 20);
        System.out.println("user abandon list:  " + abandonList.toString());

        /**
         * Activate a user.
         */
        ResponseResult active = User.activate(user);
        System.out.println("user activate set:  " + active.toString());

        /**
         * Reactivate user IDs.
         */
        UserModel user1 = new UserModel().setIds(new String[]{"CHIQ1", "CHIQ2"});
        ResponseResult reactivate = User.reactivate(user1);
        System.out.println("user reactivate set:  " + reactivate.toString());

        /**
         *
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * Query the groups a user belongs to
         */
        UserGroupQueryResult userGroupResult = User.getGroups(user);
        System.out.println("getGroups:  " + userGroupResult.toString());


        ExpireModel expireModel = new ExpireModel()
                .setUserId(new String[]{"CHIQ1", "CHIQ2"})
                .setTime(1623123911000L);
        /**
         *
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * Token expiration
         */
        refreshResult = User.expire(expireModel);
        System.out.println("expire:  " + refreshResult.toString());


    }
}
