package io.rong.example.message;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.messages.*;
import io.rong.methods.message._private.Private;
import io.rong.methods.message.chatroom.Chatroom;
import io.rong.methods.message.discussion.Discussion;
import io.rong.methods.message.group.Group;
import io.rong.methods.message.history.History;
import io.rong.methods.message.system.MsgSystem;
import io.rong.methods.message.ultragroup.UltraGroup;
import io.rong.models.message.*;
import io.rong.models.push.PlatformNotification;
import io.rong.models.response.HistoryMessageResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CodeUtil;
import io.rong.util.GsonUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Message sending example
 *
 * @author RongCloud
 * @version 3.0.0
 */
public class MessageExample {
    /**
     * Replace with your App Key
     */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret
     */
    private static final String appSecret = "appSecret";

    private static final UserInfo userinfo = new UserInfo("rc1", "rc_user1",
            "http://www.rongcloud.cn/images/logo.png", "");
    private static final TxtMessage txtMessage = new TxtMessage("helloAAAA", "helloExtra");
    private static final VoiceMessage voiceMessage = new VoiceMessage("hello", "helloExtra", 20L);

    /**
     * Custom API endpoint
     */
//    private static final String api = "http://api-bj.ronghub.com";
    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
//        RongCloudConfig config = RongCloudConfig.DefaultConfig;
//        config.connectionKeepAlive = false;
//        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, config);

        // Custom API endpoint
//        RongCloud rongCloud = RongCloud.getInstance("appkey", "appSecret", api);
//        RongCloud rongCloud2 = RongCloud.getInstance("appKey", "appSecret", new RongCloudConfig("api"));

        Private Private = rongCloud.message.msgPrivate;
        MsgSystem system = rongCloud.message.system;
        Group group = rongCloud.message.group;
        Chatroom chatroom = rongCloud.message.chatroom;
        Discussion discussion = rongCloud.message.discussion;
        History history = rongCloud.message.history;
        UltraGroup ultraGroup = rongCloud.message.ultraGroup;
        PushExt.HW hw = new PushExt.HW("channelId", "NORMAL");
        // Extension parameters
        hw.addParamIfNotBlank("image", "xxx");
        PushExt pe = PushExt.build("testTitle", 1,
                hw,
                new PushExt.VIVO("1"),
                new PushExt.HONOR("importance", "image"),
                new PushExt.APNs("234353efsfwd", "232"),
                new PushExt.OPPO("134324"),
                new PushExt.FCM("134324"),
                new PushExt.OHOS("category", "image"),
                new PushExt.MI("12345", "icon_test")
        );

        /**
         * 
         *
         * Send system message
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
                .setMsgRandom(System.currentTimeMillis())
                .setContentAvailable(0);
        systemMessage.setDisableUpdateLastMsg(true);
        ResponseResult result = system.send(systemMessage);
        System.out.println("send system message:  " + result.toString());

/**
 * 
 *
 * Method to send system template messages
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
         * 
         *
         * Method to send system template messages
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
         * 
         *
         * Push-only Notification
         *
         */
        List<String> users = new ArrayList<>();
        users.add("user1");
        PushUserMessage.Notification notification = new PushUserMessage.Notification()
                .setTitle("testtitle")
                .setPushContent("testcontent");
        PlatformNotification notification1 = new PlatformNotification();
        notification1.setHw("testhw");
        notification1.setTitle("hwtitle");
        notification.setAndroid(notification1);
        PushUserMessage pushUserMessage = new PushUserMessage()
                .setUserIds(users)
                .setNotification(notification);
        ResponseResult sendUser = rongCloud.message.system.sendUser(pushUserMessage);
        System.out.println("sendUser:  " + sendUser.toString());

        /**
         * 
         *
         * Send one-to-one chat message <text, voice, file, etc.>
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
        privateMessage.setDisableUpdateLastMsg(true);
        ResponseResult privateResult = Private.send(privateMessage);
        System.out.println("send private getReqBody:  " + privateResult.getReqBody());
        System.out.println("send private message:  " + privateResult.toString());

        /**
         * 
         *
         * Send one-to-one chat template message
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
         * 
         *
         * Recall a one-to-one chat message
         * */
        RecallMessage recallMessage = new RecallMessage()
                .setSenderId("2609751433442958892")
                .setTargetId("2651280140445094444")
                .setuId("5H6P-CGC6-44QR-VB3R")
                .setSentTime("1519444243981");
        ResponseResult recallPrivateResult = (ResponseResult) Private.recall(recallMessage);
        System.out.println("recall private:  " + recallPrivateResult.toString());


        /**
         * 
         *
         * Send a one-to-one chat status message
         */
        PrivateStatusMessage statusMessage = new PrivateStatusMessage()
                .setSenderId("IotBnm9K4")
                .setTargetId(new String[]{"jf8yVWgZO"})
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage);
        ResponseResult statusMessageResult = Private.sendStatusMessage(statusMessage);
        System.out.println("private status message result:  " + statusMessageResult.toString());


        /**
         * 
         *
         * One-to-one chat - Send a typing status message
         *
         * Typing status messages are only supported for one-to-one chat text messages and do not support other message types (including custom messages)
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
         * 
         * Send a one-to-one chat gray bar message
         */
        InfoNtfMessage infoNotify = new InfoNtfMessage("Gray bar message content", "helloExtra");
        PrivateMessage p = new PrivateMessage()
                .setSenderId("BzUPcKM2B")
                .setTargetId(new String[]{"jf8yVWgZO"})
                .setObjectName(infoNotify.getType())
                .setContent(infoNotify);
        ResponseResult infoNotifyResult = Private.send(p);
        System.out.println("send private infoNotify message:  " + infoNotifyResult.toString());

        /**
         * Send a one-to-one chat read receipt message (conversation type can be set)
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
         *
         *
         * Group message
         */
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
        groupMessage.setDisableUpdateLastMsg(true);
        ResponseResult groupResult = group.send(groupMessage);

        System.out.println("Send Group message:  " + groupResult.toString());

        /**
         *
         *
         * Group targeted message
         */
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
                .setIsMentioned(0)
                .setContentAvailable(0);
        ResponseResult groupDirectionResult = group.sendDirection(groupDirectionMessage);

        System.out.println("send group direction message:  " + groupDirectionResult.toString());
        /**
         *
         *
         * Recall a group message
         * */
        recallMessage = new RecallMessage()
                .setSenderId("sea9901")
                .setTargetId("markoiwm")
                .setuId("5GSB-RPM1-KP8H-9JHF")
                .setSentTime("1522242030641");
        ResponseResult recallMessageResult = (ResponseResult) group.recall(recallMessage);

        System.out.println("recall group message:  " + recallMessageResult.toString());

        /**
         *
         *
         * Send a group @message
         * */
        // Users to be mentioned
        String[] mentionIds = {"jf8yVWgZO"};
//        String[] targetIds = { "ckHduTB4f" };
        MentionedInfo mentionedInfo = new MentionedInfo(1, mentionIds, "");
        // @content
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
                .setContentAvailable(0)
                .setExpansion(true)
                .setExtraContent(new HashMap<String, String>())
                .setToUserId(new String[]{"jf8yVWgZO"})
                ;
        ResponseResult mentionResult = rongCloud.message.group.sendMention(mentionMessage);

        System.out.println("group mention result:  " + mentionResult.toString());

        /**
         * Send group status message
         *
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
         * Send group gray bar message (to all members)
         */
        GroupMessage groupMessage2 = new GroupMessage()
                .setSenderId("BzUPcKM2B")
                .setTargetId(targetIds)
                .setObjectName(infoNotify.getType())
                .setContent(infoNotify);
        ResponseResult groupinfoNotifyResult = group.send(groupMessage2);
        System.out.println("group info Notify message result:  " + groupinfoNotifyResult.toString());

        /**
         * Send group gray bar message - targeted users (up to 1000 users per request)
         */
        String[] userIds = {"2651280140445094444", "2651280140445094445"};
        GroupMessage groupMessage3 = new GroupMessage()
                .setSenderId("BzUPcKM2B")
                .setTargetId(targetIds) // Group ID
                .setToUserId(userIds) // User IDs in the group
                .setObjectName(infoNotify.getType())
                .setContent(infoNotify);
        group.sendDirection(groupMessage3);

        /**
         * 
         *
         * Send a discussion group message
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
         * 
         *
         * Recall a discussion group message
         * */
        recallMessage = new RecallMessage()
                .setSenderId("sea9901")
                .setTargetId("IXQhMs3ny")
                .setuId("5GSB-RPM1-KP8H-9JHF")
                .setSentTime("1519444243981");
        ResponseResult recallDiscussionResult = (ResponseResult) discussion.recall(recallMessage);

        System.out.println("recall discussion message:  " + recallDiscussionResult.toString());


        /**
         * 
         *
         * Chatroom message
         * */

        String[] chatroomIds = {"15222258878654823358"};

        CustomTxtMessage ctm = new CustomTxtMessage("hello");
        ChatroomMessage chatroomMessage = new ChatroomMessage()
                .setSenderId("1")
                .setTargetId(chatroomIds)
                .setContent(ctm)
                .setIsIncludeSender(1)
                .setPriority(1)
                .setObjectName(ctm.getType());

        ResponseResult chatroomResult = chatroom.send(chatroomMessage);
        System.out.println("send chatroom message:  " + chatroomResult.toString());
        /**
         * 
         *
         * Recall a message in the chatroom
         */
        recallMessage = new RecallMessage()
                .setSenderId("1")
                .setTargetId("15222258878654823358")
                .setuId("5GSB-RPM1-KP8H-9JHF")
                .setSentTime("1522242030641")
                .setDisableUpdateLastMsg(true);
        ResponseResult recallChatroomResult = (ResponseResult) chatroom.recall(recallMessage);

        System.out.println("recall chatroom message:  " + recallChatroomResult.toString());

        /**
         * Recall a message in the ultra group
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
         * 
         *
         * Broadcast a message in the chatroom
         *
         * This feature requires a dedicated service: http://www.rongcloud.cn/deployment#overseas-cloud
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
         * 
         *
         * Retrieve historical message log files
         *
         * */

        HistoryMessageResult historyMessageResult = (HistoryMessageResult) history.get("2018032810");
        System.out.println("get history  message:  " + historyMessageResult.toString());

        /**
         *
         * 
         *
         * Delete historical message log files
         *
         * */

        ResponseResult removeHistoryMessageResult = history.remove("2018030210");
        System.out.println("remove history  message:  " + removeHistoryMessageResult.toString());

        /**
         * Clear historical messages
         *
         */
        ResponseResult cleanResult = history.clean("1", "jf8yVWgZO", "IotBnm9K4", null);
        System.out.println("clean history  message:  " + cleanResult.toString());


        PushExt pushExt = new PushExt();
        pushExt.setTitle("aaa");
        pushExt.setTemplateId("22");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("a", "a");
        UltraGroupMessage ultraGroupMessage = new UltraGroupMessage()
                .setSenderId("bN6oQi8T5")
                .setContent(txtMessage)
                .setTargetId(targetIds)
                .setPushExt(pushExt)
                .setExpansion(true)
                .setExtraContent(hashMap)
                .setObjectName(txtMessage.getType())
                .setIsCounted(1);


        /**
         * Send ultra group message
         * */
        ResponseResult send = ultraGroup.send(ultraGroupMessage);
        System.out.println("send ultragroup message:  " + send.toString());


        /**
         * Send ultra group @ message
         * */
        ultraGroupMessage.setIsMentioned(1);
        txtMessage.setMentionedInfo(new MentionedInfo(2, new String[]{"user1"}, "Someone @ you"));
        send = ultraGroup.send(ultraGroupMessage);
        System.out.println("send ultragroup mentioned message:  " + send.toString());


        /**
         * Send ultra group direction message
         * */
        ultraGroupMessage.setToUserIds(new String[]{"tid1"});
        send = ultraGroup.send(ultraGroupMessage);
        System.out.println("send ultragroup direction message:  " + send.toString());


        /**
         * Send ultra group audit message
         * */

        Audit audit = new Audit(1, "strategy");
        txtMessage.setAudit(audit);
        send = ultraGroup.send(ultraGroupMessage);
        System.out.println("send audit message:  " + send.toString());
    }
}
