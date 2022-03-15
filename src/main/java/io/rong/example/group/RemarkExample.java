package io.rong.example.group;

import com.alibaba.fastjson.JSON;
import io.rong.RongCloud;
import io.rong.models.Result;

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


        Result result = rongCloud.group.remark.set("userId", "groupId","remark");

        System.out.println("set remark: " + result.toString());

        result = rongCloud.group.remark.del("userId", "groupId1");

        System.out.println("del remark: " + result.toString());


        result = rongCloud.group.remark.get("abc","abcaa");

        System.out.println("get remark: " + result.toString());
    }
}
