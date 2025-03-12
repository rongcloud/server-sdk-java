package io.rong.example.sensitive;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.sensitive.SensitiveWord;
import io.rong.models.response.BatchAddSensitiveWordResult;
import io.rong.models.response.ListWordfilterResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.sensitiveword.AddSensitiveWordsModel;
import io.rong.models.sensitiveword.SensitiveWordModel;

import java.util.ArrayList;
import java.util.List;


public class SensitiveExample {
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
        //自定义 api 地址方式
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        SensitiveWord sensitiveWord = rongCloud.sensitiveword;

        /**
         *API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * 添加替换敏感词方法
         *
         * */
        SensitiveWordModel sentiveWord = new SensitiveWordModel()
                .setType(0)
                .setKeyword("黄赌毒")
                .setReplace("***");
        ResponseResult addesult = sensitiveWord.add(sentiveWord);
        System.out.println("sentiveWord add:  " + addesult.toString());

        /**
         *API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * 添加替换敏感词方法
         *
         * */
        sentiveWord = new SensitiveWordModel()
                .setType(1)
                .setKeyword("黄赌毒");
        ResponseResult addersult = sensitiveWord.add(sentiveWord);
        System.out.println("sentiveWord  add replace :  " + addersult.toString());

        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 查询敏感词列表方法
         *
         * */
        ListWordfilterResult result = sensitiveWord.getList(1);
        System.out.println("getList:  " + result.toString());

        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 移除敏感词方法（从敏感词列表中，移除某一敏感词。）
         *
         * */

        ResponseResult removeesult = sensitiveWord.remove("money");
        System.out.println("SensitivewordDelete:  " + removeesult.toString());


        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 批量移除敏感词方法（从敏感词列表中，批量移除某一敏感词。）
         *
         * */
        String[] words = {"黄赌毒"};
        ResponseResult batchDeleteResult = sensitiveWord.batchDelete(words);
        System.out.println("SensitivewordbatchDelete:  " + batchDeleteResult.toString());

        /**
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 批量添加敏感词方法
         */

        AddSensitiveWordsModel.SensitiveWord word1 = new AddSensitiveWordsModel.SensitiveWord().setWord("黄赌毒").setReplaceWord("***");
        AddSensitiveWordsModel.SensitiveWord word2 = new AddSensitiveWordsModel.SensitiveWord().setWord("黄赌").setReplaceWord("**");
        AddSensitiveWordsModel.SensitiveWord word3 = new AddSensitiveWordsModel.SensitiveWord().setWord("黄");

        List<AddSensitiveWordsModel.SensitiveWord> wordList = new ArrayList<>();
        wordList.add(word1);
        wordList.add(word2);
        wordList.add(word3);

        AddSensitiveWordsModel addSensitiveWordsModel = new AddSensitiveWordsModel()
                .setWords(wordList);
        BatchAddSensitiveWordResult batchAddSensitiveWordResult = sensitiveWord.batchAdd(addSensitiveWordsModel);
        System.out.println("SensitiveWordBatchAdd:  " + batchAddSensitiveWordResult.toString());
    }

}
