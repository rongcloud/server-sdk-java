package io.rong.example.profile;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.profile.EntrustUser;
import io.rong.models.profile.PagingQueryUserProfilesModel;
import io.rong.models.profile.UserProfileModel;
import io.rong.models.response.QueryUserProfilesResp;
import io.rong.models.response.ResponseResult;

/**
 * Group Information Hosting Service
 * <p>
 *
 * @author RongCloud
 * @version 3.6.0
 */
public class EntrustUserExample {

    /**
     * Replace with your App Key here
     */
    private static final String APP_KEY = "appKey";
    /**
     * Replace with your App Secret here
     */
    private static final String APP_SECRET = "secret";
    /**
     * Initialization
     */
    private static EntrustUser entrustUser = RongCloud.getInstance(APP_KEY, APP_SECRET, CenterEnum.BJ).entrustUser;



    /**
     * Local testing
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // User profile settings
        setProfileExample();

        // Batch query user profiles
        batchQueryUserProfilesExample();

        // Clear user hosting information
        cleanProfileExample();

        // Paginate through the list of all users in the application
        pagingQueryUserProfilesExample();

    }

    // Set user profile
    private static void setProfileExample() throws Exception {
        UserProfileModel userProfileModel = new UserProfileModel();
        userProfileModel.setUserProfile("{\"name\":\"name\"}");
        userProfileModel.setUserId("userId");
        userProfileModel.setUserExtProfile("{\"ext_\":\"ext\"}");
        ResponseResult responseResult = entrustUser.setProfile(userProfileModel);
        System.out.println(responseResult);
    }

    // Batch query user profiles
    private static void batchQueryUserProfilesExample() throws Exception {
        QueryUserProfilesResp userProfilesResp = entrustUser.batchQueryUserProfiles("uid1");
        System.out.println(userProfilesResp);
    }

    // Clear user profile hosting information
    private static void cleanProfileExample() throws Exception {
        ResponseResult responseResult = entrustUser.cleanProfile("uid1");
        System.out.println(responseResult);
    }

    // Paginate through the list of all users in the application
    private static void pagingQueryUserProfilesExample() throws Exception {
        PagingQueryUserProfilesModel input = new PagingQueryUserProfilesModel();
        input.setPage(1);
        input.setSize(10);
        input.setOrder(1);
        QueryUserProfilesResp userProfilesResp = entrustUser.pagingQueryUserProfiles(input);
        System.out.println(userProfilesResp);
    }

}