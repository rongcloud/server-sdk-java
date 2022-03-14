package io.rong.example.message;

import io.rong.RongCloud;
import io.rong.messages.CustomTxtMessage;
import io.rong.messages.InfoNtfMessage;
import io.rong.messages.ReadReceiptMessage;
import io.rong.messages.TxtMessage;
import io.rong.messages.TypingStatusMessage;
import io.rong.messages.UserInfo;
import io.rong.messages.VoiceMessage;
import io.rong.methods.message._private.Private;
import io.rong.methods.message.chatroom.Chatroom;
import io.rong.methods.message.discussion.Discussion;
import io.rong.methods.message.group.Group;
import io.rong.methods.message.history.History;
import io.rong.methods.message.system.MsgSystem;
import io.rong.methods.message.ultragroup.UltraGroup;
import io.rong.models.message.*;
import io.rong.models.response.HistoryMessageResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CodeUtil;
import io.rong.util.GsonUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonSyntaxException;

/**
 * 消息发送示例
 *
 * @author RongCloud
 * @version 3.0.0
 */
public class MessageExample {
    /**
     * 此处替换成您的appKey
     */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     */
    private static final String appSecret = "appSecret";

    private static final UserInfo userinfo = new UserInfo("rc1", "rc_user1",
            "http://www.rongcloud.cn/images/logo.png", "");
    private static final TxtMessage txtMessage = new TxtMessage("helloAAAA", "helloExtra");
    private static final VoiceMessage voiceMessage = new VoiceMessage("hello", "helloExtra", 20L);

    /**
     * 自定义api地址
     */
//    private static final String api = "http://api-bj.ronghub.com";
    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //自定义 api 地址方式
//        RongCloud rongCloud = RongCloud.getInstance("appkey", "appSecret", api);
//        RongCloud rongCloud2 = RongCloud.getInstance("appKey", "appSecret", new RongCloudConfig("api"));

        Private Private = rongCloud.message.msgPrivate;
        MsgSystem system = rongCloud.message.system;
        Group group = rongCloud.message.group;
        Chatroom chatroom = rongCloud.message.chatroom;
        Discussion discussion = rongCloud.message.discussion;
        History history = rongCloud.message.history;
        UltraGroup ultraGroup = rongCloud.message.ultraGroup;
        PushExt pe = PushExt.build("testTitle", 1,
                new PushExt.HW("channelId", "NORMAL"), new PushExt.VIVO("1"),
                new PushExt.APNs("234353efsfwd", "232"),
                new PushExt.OPPO("134324"));

        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/system.html#send
         *
         * 发送系统消息
         *
         */
        String[] targetIds = {"2651280140445094444"};
        SystemMessage systemMessage = new SystemMessage()
                .setSenderId("usetId")
                .setTargetId(targetIds)
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setPushContent("this is a push")
                .setPushData("{'pushData':'hello'}")
                .setPushExt(pe)
                .setIsPersisted(0)
                .setIsCounted(0)
                .setContentAvailable(0);

        ResponseResult result = system.send(systemMessage);
        System.out.println("send system message:  " + result.toString());

        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/system.html#sendTemplate
         *
         * 发送系统模板消息方法
         *
         */
        Reader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(MessageExample.class.getClassLoader().getResourceAsStream("jsonsource/message/TemplateMessage.json")));
            TemplateMessage template = (TemplateMessage) GsonUtil.fromJson(reader, TemplateMessage.class);
            ResponseResult systemTemplateResult = system.sendTemplate(template);
            System.out.println("send system template message:  " + systemTemplateResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }

        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/system.html#sendTemplate
         *
         * 发送系统模板消息方法
         *
         */
        BroadcastMessage message = new BroadcastMessage()
                .setSenderId("Hji8yh76")
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setPushContent("this is a push")
                .setPushData("{'pushData':'hello'}")
                .setOs("iOS");
        ResponseResult broadcastResult = rongCloud.message.system.broadcast(message);
        System.out.println("send broadcast:  " + broadcastResult.toString());


        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/private.html#send
         *
         * 发送单聊消息<文本, 语音, 文件类型 等消息类型>
         */
        PrivateMessage privateMessage = new PrivateMessage()
                .setSenderId("2609751433442958892")
                .setTargetId(targetIds)
                .setObjectName(voiceMessage.getType())
                .setContent(voiceMessage)
                .setPushContent("")
                .setPushData("{\"pushData\":\"hello\"}")
                .setPushExt("{\"title\":\"\",\"forceShowPushContent\":0,\"pushConfigs\":{\"HW\":{\"channelId\":\"\"},\"MI\":{\"channelId\":\"\"},\"OPPO\":{\"channelId\":\"\"}}}")
                .setCount("4")
                .setVerifyBlacklist(0)
                .setIsPersisted(0)
                .setIsCounted(0)
                .setIsIncludeSender(0);
        ResponseResult privateResult = Private.send(privateMessage);
        System.out.println("send private getReqBody:  " + privateResult.getReqBody());
        System.out.println("send private message:  " + privateResult.toString());

        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/private.html#sendTemplate
         *
         * 发送单聊模板消息方法
         */
        try {
            reader = new BufferedReader(new InputStreamReader(MessageExample.class.getClassLoader().getResourceAsStream("jsonsource/message/TemplateMessage.json")));
            TemplateMessage template = (TemplateMessage) GsonUtil.fromJson(reader, TemplateMessage.class);
            ResponseResult messagePublishTemplateResult = Private.sendTemplate(template);

            System.out.println("send privateTemplate message:  " + messagePublishTemplateResult.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }
        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/private.html#recall
         *
         * 撤回单聊消息
         * */
        RecallMessage recallMessage = new RecallMessage()
                .setSenderId("2609751433442958892")
                .setTargetId("2651280140445094444")
                .setuId("5H6P-CGC6-44QR-VB3R")
                .setSentTime("1519444243981");
        ResponseResult recallPrivateResult = (ResponseResult) Private.recall(recallMessage);
        System.out.println("recall private:  " + recallPrivateResult.toString());


        /**
         * API 文档: https://docs.rongcloud.cn/im/server/message/#statusmessage_private
         *
         * 发送单聊状态消息
         */
        PrivateStatusMessage statusMessage = new PrivateStatusMessage()
                .setSenderId("IotBnm9K4")
                .setTargetId(new String[]{"jf8yVWgZO"})
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage);
        ResponseResult statusMessageResult = Private.sendStatusMessage(statusMessage);
        System.out.println("private status message result:  " + statusMessageResult.toString());


        /**
         * API 文档: https://www.rongcloud.cn/docs/message_architecture.html#typing_status_message
         *
         * 单聊-发送正在输入状态消息
         *
         * 正在输入状态消息只支持单聊文本消息，不支持其他消息类型(包括自定义消息)
         */
        TypingStatusMessage typpingStatusMessage = new TypingStatusMessage();
        PrivateMessage privateMsg = new PrivateMessage()
                .setSenderId("BzUPcKM2B")
                .setTargetId(new String[]{"jf8yVWgZO"})
                .setObjectName(typpingStatusMessage.getType())
                .setContent(typpingStatusMessage);
        ResponseResult statusResult = Private.sendTypingStatusMessage(privateMsg);
        System.out.println("send private message:  " + statusResult.toString());

        /**
         * API 文档: https://docs.rongcloud.cn/im/introduction/message_structure/#InfoNtf
         * 发送单聊小灰条消息
         */
        InfoNtfMessage infoNotify = new InfoNtfMessage("小灰条消息内容", "helloExtra");
        PrivateMessage p = new PrivateMessage()
                .setSenderId("BzUPcKM2B")
                .setTargetId(new String[]{"jf8yVWgZO"})
                .setObjectName(infoNotify.getType())
                .setContent(infoNotify);
        ResponseResult infoNotifyResult = Private.send(p);
        System.out.println("send private infoNotify message:  " + infoNotifyResult.toString());

        /**
         * 发送单聊已读回执消息(会话类型可设置)
         */
        ReadReceiptMessage receiptMessage = new ReadReceiptMessage("1589425641984", "BI24-J9K0-0007-VD72", CodeUtil.ConversationType.PRIVATE.getIntValue());
        PrivateMessage privateReceipt = new PrivateMessage()
                .setSenderId("BzUPcKM2B")
                .setTargetId(new String[]{"jf8yVWgZO"})
                .setObjectName(receiptMessage.getType())
                .setContent(receiptMessage);
        ResponseResult privateReceiptResult = Private.send(privateReceipt);
        System.out.println("send private ReceiptResult message:  " + privateReceiptResult.toString());

        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/group.html#send
         *
         * 群组消息
         * */
        GroupMessage groupMessage = new GroupMessage()
                .setSenderId("userId")
                .setTargetId(targetIds)
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setPushContent("this is a push")
                .setPushData("{\"pushData\":\"hello\"}")
                .setPushExt(pe)
                .setIsPersisted(0)
                .setIsIncludeSender(0)
                .setContentAvailable(0);
        ResponseResult groupResult = group.send(groupMessage);

        System.out.println("send Group message:  " + groupResult.toString());
        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/group.html#send
         *
         * 群组定向消息
         * */
        String[] targetId = {"2651280140445094444"};
        String[] toUserIds = {"2651280140445094444"};
        GroupMessage groupDirectionMessage = new GroupMessage()
                .setSenderId("userId")
                .setTargetId(targetId)
                .setToUserId(toUserIds)
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setPushContent("this is a push")
                .setPushData("{\"pushData\":\"hello\"}")
                .setPushExt(pe)
                .setIsPersisted(0)
                .setIsIncludeSender(0)
                .setContentAvailable(0);
        ResponseResult groupDirectionResult = group.sendDirection(groupDirectionMessage);

        System.out.println("send group direction message:  " + groupDirectionResult.toString());
        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/group.html#recall
         *
         * 群组撤回消息
         * */
        recallMessage = new RecallMessage()
                .setSenderId("sea9901")
                .setTargetId("markoiwm")
                .setuId("5GSB-RPM1-KP8H-9JHF")
                .setSentTime("1522242030641");
        ResponseResult recallMessageResult = (ResponseResult) group.recall(recallMessage);

        System.out.println("recall group message:  " + recallMessageResult.toString());

        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/group.html#sendMention
         *
         * 群组@消息
         * */
        //要@的人
        String[] mentionIds = {"jf8yVWgZO"};
//        String[] targetIds = { "ckHduTB4f" };
        MentionedInfo mentionedInfo = new MentionedInfo(1, mentionIds, "");
        //@内容
        MentionMessageContent content = new MentionMessageContent(txtMessage, mentionedInfo);

        MentionMessage mentionMessage = new MentionMessage()
                .setSenderId("BzUPcKM2B")
                .setTargetId(targetIds)
                .setObjectName(txtMessage.getType())
                .setContent(content)
                .setPushContent("this is a push")
                .setPushData("{\"pushData\":\"hello\"}")
                .setPushExt(GsonUtil.toJson(pe, PushExt.class))
                .setIsPersisted(0)
                .setIsCounted(0)
                .setIsIncludeSender(0)
                .setContentAvailable(0);
        ResponseResult mentionResult = rongCloud.message.group.sendMention(mentionMessage);

        System.out.println("group mention result:  " + mentionResult.toString());

        /**
         * 发送群组状态消息
         *
         * API 文档: https://docs.rongcloud.cn/im/server/message/#_6
         */
        GroupStatusMessage groupStatusMessage = new GroupStatusMessage();
        groupStatusMessage.setSenderId("BzUPcKM2B")
                .setGroupId(new String[]{"ckHduTB4f"})
                .setContent(txtMessage)
                .setObjectName(txtMessage.getType())
                .setVerifyBlacklist(0)
                .setIsIncludeSender(1);
        ResponseResult groupStatusResult = group.sendStatusMessage(groupStatusMessage);
        System.out.println("group status message result:  " + groupStatusResult.toString());

        /**
         * 发送群聊小灰条消息（所有人）
         */
        GroupMessage groupMessage2 = new GroupMessage()
                .setSenderId("BzUPcKM2B")
                .setTargetId(targetIds)
                .setObjectName(infoNotify.getType())
                .setContent(infoNotify);
        ResponseResult groupinfoNotifyResult = group.send(groupMessage2);
        System.out.println("group info Notify message result:  " + groupinfoNotifyResult.toString());

        /**
         * 发送群聊小灰条消息-定向用户(单次请求最多 1000 人）
         */
        String[] userIds = {"2651280140445094444", "2651280140445094445"};
        GroupMessage groupMessage3 = new GroupMessage()
                .setSenderId("BzUPcKM2B")
                .setTargetId(targetIds)// 群Id
                .setToUserId(userIds)// 群里的用户Id
                .setObjectName(infoNotify.getType())
                .setContent(infoNotify);
        group.sendDirection(groupMessage3);

        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/discussion.html#send
         *
         * 发送讨论组消息
         * */
        String[] discussionIds = {"lijhGk87", "lijhGk88"};
        DiscussionMessage discussionMessage = new DiscussionMessage()
                .setSenderId("JuikH78ko")
                .setTargetId(discussionIds)
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setPushContent("this is a push")
                .setPushData("{\"pushData\":\"hello\"}")
                .setIsPersisted(0)
                .setIsCounted(0)
                .setIsIncludeSender(0)
                .setContentAvailable(0);

        ResponseResult discussionResult = discussion.send(discussionMessage);

        System.out.println("send discussion message:  " + discussionResult.toString());

        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/discussion.html#recall
         *
         * 撤回讨论组消息
         * */
        recallMessage = new RecallMessage()
                .setSenderId("sea9901")
                .setTargetId("IXQhMs3ny")
                .setuId("5GSB-RPM1-KP8H-9JHF")
                .setSentTime("1519444243981");
        ResponseResult recallDiscussionResult = (ResponseResult) discussion.recall(recallMessage);

        System.out.println("recall discussion message:  " + recallDiscussionResult.toString());


        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/chatroom.html#send
         *
         * 聊天室消息
         * */

        String[] chatroomIds = {"15222258878654823358"};

        CustomTxtMessage ctm = new CustomTxtMessage("hello");
        ChatroomMessage chatroomMessage = new ChatroomMessage()
                .setSenderId("1")
                .setTargetId(chatroomIds)
                .setContent(ctm)
                .setIsIncludeSender(1)
                .setObjectName(ctm.getType());

        ResponseResult chatroomResult = chatroom.send(chatroomMessage);
        System.out.println("send chatroom message:  " + chatroomResult.toString());
        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/chatroom.html#recall
         *
         * 聊天室撤回消息
         * */
        recallMessage = new RecallMessage()
                .setSenderId("1")
                .setTargetId("15222258878654823358")
                .setuId("5GSB-RPM1-KP8H-9JHF")
                .setSentTime("1522242030641");
        ResponseResult recallChatroomResult = (ResponseResult) chatroom.recall(recallMessage);

        System.out.println("recall chatroom message:  " + recallChatroomResult.toString());

        /**
         * 超级群消息撤回
         */
        recallMessage = new RecallMessage()
                .setSenderId("1")
                .setTargetId("15222258878654823358")
                .setuId("5GSB-RPM1-KP8H-9JHF")
                .setSentTime("1522242030641");
        ResponseResult recallUltraGroupResult = (ResponseResult) ultraGroup.recall(recallMessage);

        System.out.println("recall ultragroup message:  " + recallUltraGroupResult.toString());


        /**
         *
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/chatroom.html#broadcast
         *
         * 聊天室广播消息
         *
         * 此功能需开通专有服务: http://www.rongcloud.cn/deployment#overseas-cloud
         *
         * */
        chatroomMessage = new ChatroomMessage()
                .setSenderId("bN6oQi8T5")
                .setContent(txtMessage)
                .setObjectName(txtMessage.getType());

        ResponseResult chatroomBroadcastresult = chatroom.broadcast(chatroomMessage);
        System.out.println("send chatroom broadcast message:  " + chatroomBroadcastresult.toString());


        /**
         *
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/history.html#get
         *
         * 获取历史消息日志文件
         *
         * */

        HistoryMessageResult historyMessageResult = (HistoryMessageResult) history.get("2018032810");
        System.out.println("get history  message:  " + historyMessageResult.toString());

        /**
         *
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/message/history.html#get
         *
         * 删除历史消息日志文件
         *
         * */

        ResponseResult removeHistoryMessageResult = history.remove("2018030210");
        System.out.println("remove history  message:  " + removeHistoryMessageResult.toString());

        /**
         * 清除历史消息
         *
         * API 文档: https://docs.rongcloud.cn/im/server/message_clean/
         */
        ResponseResult cleanResult = history.clean("1", "jf8yVWgZO", "IotBnm9K4", null);
        System.out.println("clean history  message:  " + cleanResult.toString());

        PushExt pushExt = new PushExt();
        pushExt.setTitle("aaa");
        pushExt.setTemplateId("22");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("a","a");
        UltraGroupMessage ultraGroupMessage = new UltraGroupMessage()
                .setSenderId("bN6oQi8T5")
                .setContent(txtMessage)
                .setTargetId(targetIds)
                .setPushExt(pushExt)
                .setExpansion(true)
                .setExtraContent(hashMap)
                .setObjectName(txtMessage.getType());

        ResponseResult send = ultraGroup.send(ultraGroupMessage);
        System.out.println("send ultragroup message:  " + send.toString());
    }
}
