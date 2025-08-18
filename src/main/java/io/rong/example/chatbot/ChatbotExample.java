package io.rong.example.chatbot;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.chatbot.Chatbot;
import io.rong.models.bot.ChatbotInfoModel;
import io.rong.models.bot.ChatbotIntegration;
import io.rong.models.bot.SetChatbotIntegration;
import io.rong.models.response.*;

import java.util.List;

/**
 * Chatbot test example
 *
 * @author RongCloud
 */
public class ChatbotExample {

    /**
     * Replace with your App Key
     */
    private static final String APP_KEY = "appKey";
    /**
     * Replace with your App Secret
     */
    private static final String APP_SECRET = "secret";
    /**
     * Initialization
     */
    private static Chatbot chatbot = RongCloud.getInstance(APP_KEY, APP_SECRET, CenterEnum.BJ).chatbot;

    /**
     * Local test call
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //  Create chatbot test
        createTest();
        // Delete chatbot test
        deleteTest();
        // Query chatbot list test
        queryChatbotListTest();
        // Get chatbot test
        getChatbotTest();
        // Update chatbot test
        updateTest();
        // Add chatbot integration test
        createIntegrationTest();
        // Delete chatbot integration test
        deleteIntegrationTest();
        // Update chatbot integration list test
        updateIntegrationTest();
    }


    private static void createTest() throws Exception {
        ChatbotInfoModel info = new ChatbotInfoModel();
        info.setUserId("bot_uid1");
        info.setName("name");
        info.setType("type");
        info.setProfileUrl("profileUrl");
        info.setMetadata(new java.util.HashMap<String, String>());
        List<ChatbotIntegration> integrations = new java.util.ArrayList<>();
        ChatbotIntegration chatbotIntegration = new ChatbotIntegration();
        chatbotIntegration.setType("webhook");
        chatbotIntegration.setCallbackUrl("callbackUrl");
        integrations.add(chatbotIntegration);
        info.setIntegrations(integrations);
        ResponseResult result = chatbot.create(info);
        System.out.println("create chatbot test:  " + result.toString());
    }

    private static void updateIntegrationTest() throws Exception {
        SetChatbotIntegration info = new SetChatbotIntegration();
        info.setCallbackUrl("https://ws");
        info.setUserId("bot_uid1");
        info.setType("webhook");
        ResponseResult result = chatbot.updateIntegration(info);
        System.out.println("update chat bot integration test:  " + result.toString());
    }

    private static void deleteIntegrationTest() throws Exception {
        ResponseResult result = chatbot.deleteIntegration("bot_uid1", "webhook");
        System.out.println("delete chat bot integration test:  " + result.toString());
    }


    private static void createIntegrationTest() throws Exception {
        SetChatbotIntegration chatbotIntegration = new SetChatbotIntegration();
        chatbotIntegration.setType("webhook");
        chatbotIntegration.setCallbackUrl("callbackUrl2");
        chatbotIntegration.setUserId("bot_uid1");
        ResponseResult result = chatbot.createIntegration(chatbotIntegration);
        System.out.println("add chat bot integration test:  " + result.toString());
    }


    private static void updateTest() throws Exception {
        ChatbotInfoModel info = new ChatbotInfoModel();
        info.setUserId("bot_uid1");
        info.setName("name");
        info.setType("type");
        info.setProfileUrl("profileUrl1");
        info.setMetadata(new java.util.HashMap<String, String>());
        List<ChatbotIntegration> integrations = new java.util.ArrayList<>();
        ChatbotIntegration chatbotIntegration = new ChatbotIntegration();
        chatbotIntegration.setType("webhook");
        chatbotIntegration.setCallbackUrl("callbackUrl1");
        integrations.add(chatbotIntegration);
        info.setIntegrations(integrations);
        ResponseResult result = chatbot.update(info);
        System.out.println("update chatbot test:  " + result.toString());
    }

    private static void getChatbotTest() throws Exception {
        QueryChatbotsResult result = chatbot.get("bot_uid1");
        System.out.println("get chatbot test:  " + result.toString());
    }

    private static void queryChatbotListTest() throws Exception {
        PagingQueryChatbotsResult result = chatbot.query(1, 2, true);
        System.out.println("query chatbot test:  " + result.toString());
    }

    private static void deleteTest() throws Exception {
        ResponseResult result = chatbot.delete("bot_uid1");
        System.out.println("delete chatbot test:  " + result.toString());
    }


}