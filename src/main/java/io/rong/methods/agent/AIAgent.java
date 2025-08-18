package io.rong.methods.agent;

import io.rong.RongCloud;
import io.rong.methods.BaseMethod;
import io.rong.models.CheckMethod;
import io.rong.models.agent.AgentModel;
import io.rong.models.agent.ChatModel;
import io.rong.models.response.*;
import io.rong.util.GsonUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * chatbot service
 */
public class AIAgent extends BaseMethod {

    private static final String API_JSON_PATH = "agent";

    public AIAgent(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
        initPath();
    }

    @Override
    protected void initPath() {
        super.path = API_JSON_PATH;
    }

    /**
     * create agent
     **/
    public ResponseResult create(AgentModel info) throws Exception {
        String method = CheckMethod.AGENT_CREATE;
        ResponseResult result = checkFiled(info, method, ResponseResult.class);
        if (result != null) {
            return result;
        }
        return doRequest("/v3/agent/create.json", info.toString(), method, ResponseResult.class, CONTENT_TYPE_JSON);
    }

    /**
     * get agent
     **/
    public GetAIAgentResult get(String agentId) throws Exception {
        String method = CheckMethod.AGENT_GET;
        if (StringUtils.isBlank(agentId)) {
            return new GetAIAgentResult(20005, "The parameter 'agentId' is required");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("agentId", agentId);
        return doRequest("/v3/agent/get.json", GsonUtil.toJson(params, Map.class), method, GetAIAgentResult.class, CONTENT_TYPE_JSON);
    }


    /**
     * update agent
     **/
    public ResponseResult update(AgentModel info) throws Exception {
        String method = CheckMethod.AGENT_UPDATE;
        ResponseResult result = checkFiled(info, method, ResponseResult.class);
        if (result != null) {
            return result;
        }
        return doRequest("/v3/agent/update.json", info.toString(), method, ResponseResult.class, CONTENT_TYPE_JSON);
    }


    /**
     * delete agent
     **/
    public ResponseResult delete(String agentId) throws Exception {
        String method = CheckMethod.AGENT_DELETE;
        if (StringUtils.isBlank(agentId)) {
            return new ResponseResult(20005, "The parameter 'agentId' is required");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("agentId", agentId);
        return doRequest("/v3/agent/delete.json", GsonUtil.toJson(params, Map.class), method, ResponseResult.class, CONTENT_TYPE_JSON);
    }

    /**
     * query agent
     **/
    public PagingQueryAgentsResult query(Integer page, Integer size, String status, String type) throws Exception {
        String method = CheckMethod.AGENT_LIST;
        Map<String, Object> params = new HashMap<>();
        if (page != null) {
            params.put("page", page);
        }
        if (size != null) {
            params.put("size", size);
        }
        if (status != null) {
            params.put("status", status);
        }
        if (StringUtils.isBlank(type)) {
            params.put("type", type);
        }
        return doRequest("/v3/agent/query.json", GsonUtil.toJson(params, Map.class), method, PagingQueryAgentsResult.class, CONTENT_TYPE_JSON);
    }

    /**
     * query agent
     **/
    public ChatAgentResult chat(ChatModel model) throws Exception {
        String method = CheckMethod.AGENT_CHAT;
        ChatAgentResult result = checkFiled(model, method, ChatAgentResult.class);
        if (result != null) {
            return result;
        }
        return doRequest("/v3/chat/generate.json", model.toString(), method, ChatAgentResult.class, CONTENT_TYPE_JSON);
    }


}