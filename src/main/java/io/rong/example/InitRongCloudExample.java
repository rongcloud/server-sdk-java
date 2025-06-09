package io.rong.example;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.RongCloudConfig;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;

/**
 * @author rongcloud
 */
public class InitRongCloudExample {

    /**
     * Replace it with your App Key
     */
    private static final String appKey = "your_app_key";
    /**
     * Replace it with your App Secret
     */
    private static final String appSecret = "your_app_secret";

    public static void main(String[] args) throws Exception {

        //Method 1， use the default api host
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
        TokenResult result = rongCloud.user.register(new UserModel("uid", "name", "xxx"));
        System.out.println("getToken:  " + result.toString());

        // Method 2, use the specified api host
        rongCloud = RongCloud.getInstance(appKey, appSecret, "https://api.rong-api.com", "https://api-b.rong-api.com");
        result = rongCloud.user.register(new UserModel("uid", "name", "xxx"));
        System.out.println("getToken:  " + result.toString());

        // Method 3, use the specified api host, and set the http connection timeout and read timeout
        RongCloudConfig cloudConfig = new RongCloudConfig("https://api.rong-api.com", null);
        cloudConfig.httpConnectTimeout = 30000;
        rongCloud = RongCloud.getInstance(appKey, appSecret, cloudConfig);
        result = rongCloud.user.register(new UserModel("uid", "name", "xxx"));
        System.out.println("getToken:  " + result.toString());
    }
}
