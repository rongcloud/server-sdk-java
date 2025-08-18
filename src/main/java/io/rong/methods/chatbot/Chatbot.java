package io.rong.methods.chatbot;

import io.rong.RongCloud;
import io.rong.methods.BaseMethod;
import io.rong.models.CheckMethod;
import io.rong.models.bot.ChatbotInfoModel;
import io.rong.models.bot.SetChatbotIntegration;
import io.rong.models.response.PagingQueryChatbotsResult;
import io.rong.models.response.QueryChatbotsResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.GsonUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * chatbot service
 */
public class Chatbot extends BaseMethod {

    private static final String API_JSON_PATH = "chatbot";

    public Chatbot(String appKey, String appSecret, RongCloud rongCloud) {
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
     * create bot
     **/
    public ResponseResult create(ChatbotInfoModel info) throws Exception {
        String method = CheckMethod.BOT_CREATE;
        ResponseResult result = checkFiled(info, method, ResponseResult.class);
        if (result != null) {
            return result;
        }
        return doRequest("/v3/bot/create.json", info.toString(), method, ResponseResult.class, CONTENT_TYPE_JSON);
    }

    /**
     * list chatbot
     */
    public PagingQueryChatbotsResult query(Integer page, Integer pageSize, Boolean includeIntegration) throws Exception {
        String method = CheckMethod.BOT_LIST;
        Map<String, Object> params = new HashMap<>();
        if (page != null) {
            params.put("page", page);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
        if (includeIntegration != null) {
            params.put("includeIntegration", includeIntegration);
        }
        return doRequest("/v3/bot/query.json", GsonUtil.toJson(params, Map.class), method, PagingQueryChatbotsResult.class, CONTENT_TYPE_JSON);
    }


    /**
     * get chatbot
     */
    public QueryChatbotsResult get(String... userIds) throws Exception {
        String method = CheckMethod.BOT_GET;
        if (userIds == null) {
            return new QueryChatbotsResult(20005, "The parameter 'userIds' is required");
        }
        QueryChatbotsResult result = checkParam("userIds", userIds, method, QueryChatbotsResult.class);
        if (result != null) {
            return result;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("userIds", userIds);
        return doRequest("/v3/bot/get.json", GsonUtil.toJson(params, Map.class), method, QueryChatbotsResult.class, CONTENT_TYPE_JSON);
    }


    /**
     * delete chatbot
     */
    public ResponseResult delete(String userId) throws Exception {
        String method = CheckMethod.BOT_DELETE;
        if (StringUtils.isBlank(userId)) {
            return new ResponseResult(20005, "The parameter 'userId' is required");
        }
        ResponseResult result = checkParam("userId", userId, method, ResponseResult.class);
        if (result != null) {
            return result;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return doRequest("/v3/bot/delete.json", GsonUtil.toJson(params, Map.class), method, ResponseResult.class, CONTENT_TYPE_JSON);
    }


    /**
     * update chatbot
     */
    public ResponseResult update(ChatbotInfoModel model) throws Exception {
        String method = CheckMethod.BOT_UPDATE;
        return doRequest("/v3/bot/update.json", model.toString(), method, ResponseResult.class, CONTENT_TYPE_JSON);
    }


    /**
     * create chatbot integration
     */
    public ResponseResult createIntegration(SetChatbotIntegration integration) throws Exception {
        String method = CheckMethod.BOT_ADD_INTEGRATION;
        ResponseResult result = checkFiled(integration, method, ResponseResult.class);
        if (result != null) {
            return result;
        }
        return doRequest("/v3/bot/integration/create.json", integration.toString(), method, ResponseResult.class, CONTENT_TYPE_JSON);
    }

    /**
     * delete chatbot integration
     */

    public ResponseResult deleteIntegration(String userId, String type) throws Exception {
        String method = CheckMethod.BOT_DELETE_INTEGRATION;
        if (StringUtils.isBlank(userId)) {
            return new ResponseResult(20005, "The parameter 'userId' is required");
        }
        if (StringUtils.isBlank(type)) {
            return new ResponseResult(20005, "The parameter 'type' is required");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("type", type);
        return doRequest("/v3/bot/integration/delete.json", GsonUtil.toJson(params, Map.class), method, ResponseResult.class, CONTENT_TYPE_JSON);
    }


    /**
     * update chatbot integration
     */
    public ResponseResult updateIntegration(SetChatbotIntegration integration) throws Exception {
        String method = CheckMethod.BOT_UPDATE_INTEGRATION;
        ResponseResult result = checkFiled(integration, method, ResponseResult.class);
        if (result != null) {
            return result;
        }
        return doRequest("/v3/bot/integration/update.json", integration.toString(), method, ResponseResult.class, CONTENT_TYPE_JSON);
    }

}