package io.rong.example.sensitive;

import io.rong.RongCloud;
import io.rong.methods.sensitive.SensitiveWord;
import io.rong.models.response.ListWordfilterResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.sensitiveword.SensitiveWordModel;

import static org.junit.Assert.assertEquals;

public class SensitiveExample {
   /**
    * 此处替换成您的appKey
    *  */
    private static final String appKey = "z3v5yqkbvy9f0";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "plhr2PA386a";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        SensitiveWord SensitiveWord = rongCloud.sensitiveword;

        /**
         *API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/sensitive/sensitive.html#add
         *
         * 添加替换敏感词方法
         *
         * */
        SensitiveWordModel sentiveWord = new SensitiveWordModel()
                .setType(0)
                .setKeyWord("黄赌毒")
                .setReplace("***");
        ResponseResult addesult = SensitiveWord.add(sentiveWord);
        System.out.println("sentiveWord add:  " + addesult.toString());

        /**
         *API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/sensitive/sensitive.html#add
         *
         * 添加替换敏感词方法
         *
         * */
        sentiveWord = new SensitiveWordModel()
                .setType(1)
                .setKeyWord("黄赌毒");
        ResponseResult addersult = SensitiveWord.add(sentiveWord);
        System.out.println("sentiveWord add:  " + addersult.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/sensitive/sensitive.html#getList
         * 查询敏感词列表方法
         *
         * */
        ListWordfilterResult result = SensitiveWord.getList(1);
        System.out.println("getList:  " + result.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/sensitive/sensitive.html#remove
         * 移除敏感词方法（从敏感词列表中，移除某一敏感词。）
         *
         * */

        ResponseResult removeesult = SensitiveWord.remove("money");
        System.out.println("SensitivewordDelete:  " + removeesult.toString());

    }
}
