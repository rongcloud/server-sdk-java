package io.rong.example.profile;

import io.rong.RongCloud;
import io.rong.RongCloudConfig;
import io.rong.methods.profile.EntrustUser;
import io.rong.models.profile.*;
import io.rong.models.response.*;

/**
 * 群托管服务
 * <p>
 * API 文档: https://docs.rongcloud.cn/platform-chat-api/im-server-api-list-v1
 *
 * @author RongCloud
 * @version 3.6.0
 */
public class EntrustUserExample {

    /**
     * 此处替换成您的appKey
     */
    private static final String APP_KEY = "appKey";
    /**
     * 此处替换成您的appSecret
     */
    private static final String APP_SECRET = "secret";
    /**
     * 初始化
     */
    private static EntrustUser entrustUser = RongCloud.getInstance(APP_KEY, APP_SECRET, RongCloudConfig.DefaultConfig).entrustUser;



    /**
     * 本地调用测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        //用户资料设置
        setProfileExample();

        // 批量查询用户资料
        batchQueryUserProfilesExample();

        // 用户托管信息清除
        cleanProfileExample();

        // 分页获取应用全部用户列表
        pagingQueryUserProfilesExample();

    }

    // 用户资料设置
    private static void setProfileExample() throws Exception {
        UserProfileModel userProfileModel = new UserProfileModel();
        userProfileModel.setUserProfile("{\"name\":\"name\"}");
        userProfileModel.setUserId("userId");
        userProfileModel.setUserExtProfile("{\"ext_\":\"ext\"}");
        ResponseResult responseResult = entrustUser.setProfile(userProfileModel);
        System.out.println(responseResult);
    }

    //批量查询用户资料
    private static void batchQueryUserProfilesExample() throws Exception {
        QueryUserProfilesResp userProfilesResp = entrustUser.batchQueryUserProfiles("uid1");
        System.out.println(userProfilesResp);
    }

    // 用户托管信息清除
    private static void cleanProfileExample() throws Exception {
        ResponseResult responseResult = entrustUser.cleanProfile("uid1");
        System.out.println(responseResult);
    }

    // 分页获取应用全部用户列表
    private static void pagingQueryUserProfilesExample() throws Exception {
        PagingQueryUserProfilesModel input = new PagingQueryUserProfilesModel();
        input.setPage(1);
        input.setSize(10);
        input.setOrder(1);
        QueryUserProfilesResp userProfilesResp = entrustUser.pagingQueryUserProfiles(input);
        System.out.println(userProfilesResp);
    }

}