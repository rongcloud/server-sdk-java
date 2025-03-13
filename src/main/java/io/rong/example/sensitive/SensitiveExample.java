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
     * Replace with your App Key here
     * */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret here
     * */
    private static final String appSecret = "appSecret";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
        // Custom API URL
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        SensitiveWord sensitiveWord = rongCloud.sensitiveword;

        /**
         * 
         *
         * Add or replace sensitive words
         *
         * */
        SensitiveWordModel sentiveWord = new SensitiveWordModel()
                .setType(0)
                .setKeyword("Pornography, Gambling, Drugs")
                .setReplace("***");
        ResponseResult addesult = sensitiveWord.add(sentiveWord);
        System.out.println("sentiveWord add:  " + addesult.toString());

        /**
         * 
         *
         * Add or replace sensitive words
         */

        sentiveWord = new SensitiveWordModel()
                .setType(1)
                .setKeyword("ABC");
        ResponseResult addersult = sensitiveWord.add(sentiveWord);
        System.out.println("sentiveWord  add replace :  " + addersult.toString());

        /**
         *
         * 
         * Method to query the list of sensitive words
         *
         * */
        ListWordfilterResult result = sensitiveWord.getList(1);
        System.out.println("getList:  " + result.toString());

        /**
         *
         * 
         * Method to remove a sensitive word (Removes a specific sensitive word from the list.)
         *
         * */

        ResponseResult removeesult = sensitiveWord.remove("money");
        System.out.println("SensitivewordDelete:  " + removeesult.toString());


        /**
         *
         * 
         * Method to batch remove sensitive words (Removes multiple sensitive words from the list in bulk.)
         *
         * */
        String[] words = {"Pornography, Gambling, Drugs"};
        ResponseResult batchDeleteResult = sensitiveWord.batchDelete(words);
        System.out.println("SensitivewordbatchDelete:  " + batchDeleteResult.toString());

        /**
         * 
         * Method to batch add sensitive words
         */

        AddSensitiveWordsModel.SensitiveWord word1 = new AddSensitiveWordsModel.SensitiveWord().setWord("abc").setReplaceWord("***");
        AddSensitiveWordsModel.SensitiveWord word2 = new AddSensitiveWordsModel.SensitiveWord().setWord("ab").setReplaceWord("**");
        AddSensitiveWordsModel.SensitiveWord word3 = new AddSensitiveWordsModel.SensitiveWord().setWord("a");

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
