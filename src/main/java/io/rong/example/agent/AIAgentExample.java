package io.rong.example.agent;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.agent.AIAgent;
import io.rong.models.agent.*;
import io.rong.models.response.ChatAgentResult;
import io.rong.models.response.GetAIAgentResult;
import io.rong.models.response.PagingQueryAgentsResult;
import io.rong.models.response.ResponseResult;

/**
 * AI agent test example
 *
 * @author RongCloud
 */
public class AIAgentExample {


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
    private static AIAgent agent = RongCloud.getInstance(APP_KEY, APP_SECRET, CenterEnum.BJ).agent;


    /**
     * Local test call
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //  Create agent test
        createTest();
        //  Delete agent test
        deleteTest();
        //  Update agent test
        updateTest();
        //  Get agent test
        getTest();
        //  Query agent list test
        queryListTest();

        //  Test chat
        chatTest();
    }

    private static void chatTest() throws Exception {
        ChatModel model = new ChatModel();
        model.setAgentId("test_agentg_3821633206");
        model.setMemory(true);
        model.setConversationId("test_id");
        model.setUser("uid1");
        model.setQuery("Hello");
        ChatAgentResult result = agent.chat(model);
        System.out.println("Chat to agent test:  " + result.toString());
    }

    private static void createTest() throws Exception {
        AgentModel info = new AgentModel();
        info.setAgentId("rc_agentId");
        info.setName("rc_name");
        info.setDescription("rc_description");
        info.setType("chat");
        info.setStatus("active");
        AgentConfig agentConfig = new AgentConfig();
        Model model = new Model();
        model.setProvider("xxxx");
        model.setName("xxxx-xx");
        ModelOptions modelOptions = new ModelOptions();
        model.setOptions(modelOptions);
        agentConfig.setModel(model);
        Prompt prompt = new Prompt();
//        prompt.setId("temp_id");
        agentConfig.setPrompt(prompt);
        Memory memory = new Memory();
        memory.setStrategy("sliding_window");
        memory.setMaxMessages(10);
        memory.setMaxTokens(1000);
        agentConfig.setMemory(memory);
        info.setAgentConfig(agentConfig);
        ResponseResult result = agent.create(info);
        System.out.println("create agent test:  " + result.toString());
    }

    private static void getTest() throws Exception {
        GetAIAgentResult result = agent.get("rc_agentId");
        System.out.println("update agent test:  " + result.toString());
    }

    private static void deleteTest() throws Exception {
        ResponseResult result = agent.delete("rc_agentId");
        System.out.println("delete agent test:  " + result.toString());
    }

    private static void updateTest() throws Exception {
        AgentModel info = new AgentModel();
        info.setAgentId("rc_agentId");
        info.setName("rc_name111");
        info.setDescription("rc_description111");
        info.setType("chat");
        info.setStatus("active");
        AgentConfig agentConfig = new AgentConfig();
        Model model = new Model();
        model.setProvider("xxx");
        model.setName("xxxx-xx");
        ModelOptions modelOptions = new ModelOptions();
        model.setOptions(modelOptions);
        agentConfig.setModel(model);
        Prompt prompt = new Prompt();
        agentConfig.setPrompt(prompt);
        Memory memory = new Memory();
        memory.setStrategy("sliding_window");
        memory.setMaxMessages(10);
        memory.setMaxTokens(1000);
        agentConfig.setMemory(memory);
        info.setAgentConfig(agentConfig);
        ResponseResult result = agent.update(info);
        System.out.println("update agent test:  " + result.toString());
    }

    private static void queryListTest() throws Exception {
        PagingQueryAgentsResult result = agent.query(1, 2, "active", "chat");
        System.out.println("update agent test:  " + result.toString());
    }


}