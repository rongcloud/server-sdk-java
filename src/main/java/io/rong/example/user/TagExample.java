package io.rong.example.user;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.models.*;
import io.rong.models.response.*;
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
     * 此处替换成您的appKey
     * */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "appSecret";


    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);

        /**
         *
         * API 文档:
         * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * 添加标签
         *
         **/
        TagModel tagmodel = new TagModel();
        tagmodel.setTags(new String[] {"男", "90后"});
        tagmodel.setUserId("userId1");
        Result result = rongCloud.user.tag.set(tagmodel);

        System.out.println("setTag: " + result.toString());

        /**
         *
         * API 文档:
         * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * 批量添加标签
         *
         **/
        BatchTagModel batchtagmodel = new BatchTagModel();
        batchtagmodel.setTags(new String[] {"男", "90后"});
        batchtagmodel.setUserIds(new String[] {"userId1", "userId2"});
        result = rongCloud.user.tag.batchSet(batchtagmodel);

        System.out.println("batchSetTag: " + result.toString());


        /**
         *
         * API 文档:
         * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * 查询用户标签
         *
         **/
        GetTagModel tag = new GetTagModel();
        tag.setUserIds(new String[] {"userId1", "userId2"});
        GetTagResult result1 = rongCloud.user.tag.get(tag);

        System.out.println("getTag: " + result1.toString());
    }
}
