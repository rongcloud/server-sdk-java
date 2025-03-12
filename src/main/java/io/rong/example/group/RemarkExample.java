package io.rong.example.group;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.models.Result;


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


    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);


        Result result = rongCloud.group.remark.set("userId", "groupId","remark");

        System.out.println("set remark: " + result.toString());

        result = rongCloud.group.remark.del("userId", "groupId1");

        System.out.println("del remark: " + result.toString());


        result = rongCloud.group.remark.get("abc","abcaa");

        System.out.println("get remark: " + result.toString());
    }
}
