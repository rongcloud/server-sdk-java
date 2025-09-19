package io.rong.example.conversation;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.conversation.Conversation;
import io.rong.models.conversation.ConversationAttrModel;
import io.rong.models.conversation.ConversationModel;
import io.rong.models.conversation.ConversationSetTopModel;
import io.rong.models.conversation.ConversationTagModel;
import io.rong.models.conversation.TagConversationsModel;
import io.rong.models.conversation.UserConversationTagModel;
import io.rong.models.response.ConversationNotificationResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.ResultData;
import io.rong.util.CodeUtil.ConversationType;
import io.rong.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Conversation Example
 * @author RongCloud
 *
 * @version 3.0.0
 */
public class ConversationExample {
    /**
     * Replace with your App Key
     * */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret
     * */
    private static final String appSecret = "appSercet";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);

        Conversation conversation = rongCloud.conversation;


//        muteDemo(conversation);
//        unMuteDemo(conversation);
//        queryMuteDemo(conversation);
//        topDemo(conversation);


//        setUserConversationTagDemo(conversation);

//        deleteUserConversationTagDemo(conversation);
//
//        queryUserConversationTagsDemo(conversation);
//
//        setConversationTagDemo(conversation);
//
//        removeConversationTagDemo(conversation);
//
//        queryTagConversationsDemo(conversation);
//
        queryConversationAttributeDemo(conversation);

    }


    public static void muteDemo(Conversation conversation) throws Exception {
        ConversationModel conversationModel = new ConversationModel()
                .setType(ConversationType.PRIVATE.getName())
                .setUserId("sdkTestU1")
                .setTargetId("sdkTestU2").setUnpushLevel(5);
        ResponseResult muteConversationResult = conversation.mute(conversationModel);
        System.out.println("muteConversationResult:  " + muteConversationResult.toString());
    }


    public static void unMuteDemo(Conversation conversation) throws Exception {
        ConversationModel conversationModel = new ConversationModel()
                .setType(ConversationType.PRIVATE.getName())
                .setUserId("UgYzcDZSisNyYaZ83WXcEk11")
                .setTargetId("2iXiqVWUAWwaKA55FuZvY31");
        ResponseResult muteConversationResult = conversation.unMute(conversationModel);
        System.out.println("unMuteConversationResult:  " + muteConversationResult.toString());
    }

    public static void queryMuteDemo(Conversation conversation) throws Exception {
        ConversationModel conversationModel = new ConversationModel()
                .setType(ConversationType.PRIVATE.getName())
                .setUserId("UgYzcDZSisNyYaZ83WXcEk11")
                .setTargetId("2iXiqVWUAWwaKA55FuZvY31");
        ConversationNotificationResult getMuteConversationResult = (ConversationNotificationResult) conversation.get(conversationModel);
        System.out.println("getMuteConversationResult:  " + getMuteConversationResult.toString());
    }


    public static void topDemo(Conversation conversation) throws Exception {
        ConversationSetTopModel setTopModel = new ConversationSetTopModel()
                .setSetTop(true)
                .setConversationType(1)
                .setTargetId("sdkTestU2")
                .setUserId("sdkTestU1");
        ResponseResult result = conversation.setTop(setTopModel);
        System.out.println("setTop:  " + result.toString());
    }


    private static void setUserConversationTagDemo(Conversation conversation) throws Exception {


        List<ConversationTagModel> tags = new ArrayList<>();
        tags.add(new ConversationTagModel("tag1","标签1"));
        tags.add(new ConversationTagModel("tag2","标签2"));
        tags.add(new ConversationTagModel("tag3","标签3"));
        tags.add(new ConversationTagModel("tag4","标签4"));

        UserConversationTagModel userConversationTagModel = new UserConversationTagModel();
        userConversationTagModel.setUserId("sdkTestU1");
        userConversationTagModel.setTags(tags);

        ResponseResult result = conversation.setUserConversationTag(userConversationTagModel);

        System.out.println("setUserConversationTag result : " + result.toString());

    }


    private static void deleteUserConversationTagDemo(Conversation conversation) throws Exception {
        String userId = "sdkTestU1";
        List<String> tagIds = new ArrayList<>();
        tagIds.add("tag1");
        tagIds.add("tag2");
        ResponseResult result = conversation.deleteUserConversationTag(userId, tagIds);
        System.out.println("deleteUserConversationTagDemo result : " + result.toString());
    }

    private static void queryUserConversationTagsDemo(Conversation conversation) throws Exception {
        String userId = "sdkTestU1";
        ResultData<UserConversationTagModel> result = conversation.queryUserConversationTags(userId);
        System.out.println("queryUserConversationTagsDemo result : " + GsonUtil.toJson(result));
    }


    public static void setConversationTagDemo(Conversation conversation) throws Exception{

        TagConversationsModel tagConversationsModel = new TagConversationsModel();
        tagConversationsModel.setUserId("sdkTestU1");
        tagConversationsModel.setTagId("tag3");
        TagConversationsModel.Conversation conversation1 = new TagConversationsModel.Conversation("sdkTestU2",1);
        TagConversationsModel.Conversation conversation2 = new TagConversationsModel.Conversation("sdkTestG1",3);
        List<TagConversationsModel.Conversation> conversationList= new ArrayList<>();
        conversationList.add(conversation1);
        conversationList.add(conversation2);
        tagConversationsModel.setConversations(conversationList);
        ResponseResult result = conversation.setConversationTag(tagConversationsModel);
        System.out.println("setConversationTagDemo result: " + GsonUtil.toJson(result));
    }


    public static void removeConversationTagDemo(Conversation conversation) throws Exception{

        TagConversationsModel tagConversationsModel = new TagConversationsModel();
        tagConversationsModel.setUserId("sdkTestU1");
        tagConversationsModel.setTagId("tag3");
        TagConversationsModel.Conversation conversation1 = new TagConversationsModel.Conversation("sdkTestU2",1);
        TagConversationsModel.Conversation conversation2 = new TagConversationsModel.Conversation("sdkTestG2",3);
        List<TagConversationsModel.Conversation> conversationList= new ArrayList<>();
        conversationList.add(conversation1);
        conversationList.add(conversation2);
        tagConversationsModel.setConversations(conversationList);
        ResponseResult result = conversation.removeConversationTag(tagConversationsModel);
        System.out.println("removeConversationTagDemo result: " + GsonUtil.toJson(result));
    }



    public static void queryTagConversationsDemo(Conversation conversation) throws Exception{
        String userId = "sdkTestU1";
        String tagId = "tag3";
        ResultData<TagConversationsModel> resultData = conversation.queryTagConversations(userId,tagId);
        System.out.println("queryTagConversationsDemo result: " + GsonUtil.toJson(resultData));
    }


    public static void queryConversationAttributeDemo(Conversation conversation) throws Exception{
        String userId = "sdkTestU1";
        String targetId = "sdkTestU2";
        Integer conversationType = 1;
        ResultData<ConversationAttrModel> resultData = conversation.queryConversationAttribute(userId, targetId, conversationType);
        System.out.println("queryConversationAttributeDemo result: " + GsonUtil.toJson(resultData));
    }



}
