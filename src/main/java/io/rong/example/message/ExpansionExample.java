package io.rong.example.message;


import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.messages.CustomTxtMessage;
import io.rong.messages.InfoNtfMessage;
import io.rong.messages.ReadReceiptMessage;
import io.rong.messages.TxtMessage;
import io.rong.messages.TypingStatusMessage;
import io.rong.messages.UserInfo;
import io.rong.messages.VoiceMessage;
import io.rong.methods.message.expansion.Expansion;
import io.rong.methods.message.chatroom.Chatroom;
import io.rong.methods.message.discussion.Discussion;
import io.rong.methods.message.group.Group;
import io.rong.methods.message.history.History;
import io.rong.methods.message.system.MsgSystem;
import io.rong.models.Result;
import io.rong.models.message.*;
import io.rong.models.response.ExpansionResult;
import io.rong.models.response.HistoryMessageResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CodeUtil;
import io.rong.util.GsonUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**
 * Message Expansion Example
 *
 * @author RongCloud
 * @version 3.2.15
 */
public class ExpansionExample {

    /**
     * Replace with your App Key
     */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret
     */
    private static final String appSecret = "appSecret";


    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
        Expansion expansion = rongCloud.expansion;

        /**
         *
         * Set message extension
         *
         */
        ExpansionModel msg = new ExpansionModel();
        msg.setMsgUID("BS45-NPH4-HV87-10LM");
        msg.setUserId("WNYZbMqpH");
        msg.setTargetId("tjw3zbMrU");
        msg.setConversationType(1);
        HashMap<String, String> kv = new HashMap<String, String>();
        kv.put("type1", "1");
        kv.put("type2", "2");
        kv.put("type3", "3");
        kv.put("type4", "4");
        msg.setExtraKeyVal(kv);
        msg.setIsSyncSender(1);
        ResponseResult result = expansion.set(msg);
        System.out.println("set expansion:  " + result.toString());

        /**
         *
         * Delete message extension
         *
         */
        Set eKey = new HashSet();
        eKey.add("type1");
        eKey.add("type2");
        msg.setExtraKey(eKey);
        msg.setIsSyncSender(1);
        result = expansion.remove(msg);
        System.out.println("remove expansion:  " + result.toString());

        /**
         *
         * Get extension information
         *
         */
        ExpansionResult eResult = (ExpansionResult)expansion.getList("BS45-NPH4-HV87-10LM");
        System.out.println("getList expansion:  " + eResult.toString());

    }

}
