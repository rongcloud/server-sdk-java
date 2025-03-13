package io.rong.example.user;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.models.Result;
import io.rong.models.response.GetTagResult;
import io.rong.models.user.BatchTagModel;
import io.rong.models.user.GetTagModel;
import io.rong.models.user.TagModel;

/**
 * Demo class
 *
 * @author RongCloud
 *
 */
public class TagExample {
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

        /**
         *
         * API Documentation:
         * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * Add tags
         *
         **/
        TagModel tagmodel = new TagModel();
        tagmodel.setTags(new String[]{"Male", "Post-90s"});
        tagmodel.setUserId("userId1");
        Result result = rongCloud.user.tag.set(tagmodel);

        System.out.println("setTag: " + result.toString());

        /**
         *
         * API Documentation:
         * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *

         /**
         * Batch add tags
         *
         **/
        BatchTagModel batchtagmodel = new BatchTagModel();
        batchtagmodel.setTags(new String[]{"Male", "Post-90s"});
        batchtagmodel.setUserIds(new String[]{"userId1", "userId2"});
        result = rongCloud.user.tag.batchSet(batchtagmodel);

        System.out.println("batchSetTag: " + result.toString());


        /**
         *
         * API Documentation:
         * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * Query user tags
         *
         **/
        GetTagModel tag = new GetTagModel();
        tag.setUserIds(new String[] {"userId1", "userId2"});
        GetTagResult result1 = rongCloud.user.tag.get(tag);

        System.out.println("getTag: " + result1.toString());
    }
}
