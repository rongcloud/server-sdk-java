package io.rong.example.user;

import com.alibaba.fastjson.JSON;
import io.rong.RongCloud;
import io.rong.models.Result;
import io.rong.models.response.GetTagResult;
import io.rong.models.user.BatchTagModel;
import io.rong.models.user.GetTagModel;
import io.rong.models.user.RemarkModel;
import io.rong.models.user.TagModel;
import io.rong.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo class
 *
 * @author RongCloud
 *
 */
public class RemarkExample {
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
    private static final String api = "http://api-cn.ronghub.com";


    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        List<RemarkModel> remarks = new ArrayList<>();
        RemarkModel remarkModel = new RemarkModel();
        remarkModel.setId("user1");
        remarkModel.setRemark("remark1");
        remarks.add(remarkModel);


        Result result = rongCloud.user.remark.set("userId", JSON.toJSONString(remarks));

        System.out.println("set remark: " + result.toString());

        result = rongCloud.user.remark.del("userId", "user1");

        System.out.println("del remark: " + result.toString());


        result = rongCloud.user.remark.get("userId");

        System.out.println("get remark: " + result.toString());
    }
}
