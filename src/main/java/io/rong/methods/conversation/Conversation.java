package io.rong.methods.conversation;

import io.rong.methods.BaseMethod;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.agent.AgentModel;
import io.rong.models.conversation.ConversationAttrModel;
import io.rong.models.conversation.ConversationModel;
import io.rong.models.conversation.ConversationSetTopModel;
import io.rong.models.conversation.ConversationTagModel;
import io.rong.models.conversation.TagConversationsModel;
import io.rong.models.conversation.UserConversationTagModel;
import io.rong.models.response.ConversationNotificationResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.ResultData;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Conversation Do Not Disturb Service
 *
 * @version
 * */
public class Conversation extends BaseMethod {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "conversation";

    private static final Integer PARAM_ERROR = 1002;

    private static final String ERR_REQUIRED_TEMPLATE = "The parameter %s is required.";

    @Override
    protected void initPath() {
        super.path = PATH;
    }

    public Conversation(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        initPath();
    }

    /**
     * Set whether to receive message notifications for a specific conversation.
     *
     * @param conversation Conversation information, where type is required
     * @return ResponseResult
     **/
    public ResponseResult mute(ConversationModel conversation) throws Exception {
        String message = CommonUtil.checkFiled(conversation, PATH, CheckMethod.MUTE);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode(conversation.type.toString(), UTF8));
        sb.append("&requestId=").append(URLEncoder.encode(conversation.userId.toString(), UTF8));
        sb.append("&targetId=").append(URLEncoder.encode(conversation.targetId.toString(), UTF8));
        sb.append("&isMuted=").append(URLEncoder.encode("1", UTF8));
        if (conversation.busChannel != null) {
            sb.append("&busChannel=").append(URLEncoder.encode(conversation.getBusChannel(), UTF8));
        }
        sb.append("&unpushLevel=").append(URLEncoder.encode(String.valueOf(conversation.getUnpushLevel()), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/conversation/notification/set.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.MUTE, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * Set whether to receive message notifications for a specific conversation.
     *
     * @param conversation Conversation information, where type is required.
     * @return ResponseResult
     **/
    public ResponseResult unMute(ConversationModel conversation) throws Exception {
        String message = CommonUtil.checkFiled(conversation, PATH, CheckMethod.UNMUTE);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode(conversation.type.toString(), UTF8));
        sb.append("&requestId=").append(URLEncoder.encode(conversation.userId.toString(), UTF8));
        sb.append("&targetId=").append(URLEncoder.encode(conversation.targetId.toString(), UTF8));
        sb.append("&isMuted=").append(URLEncoder.encode("0", UTF8));
        if (conversation.busChannel != null) {
            sb.append("&busChannel=").append(URLEncoder.encode(conversation.getBusChannel(), UTF8));
        }
        sb.append("&unpushLevel=").append(URLEncoder.encode("0", UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/conversation/notification/set.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.UNMUTE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }
    /**
     * Method to query the Do Not Disturb status of a conversation.
     *
     * @param conversation Conversation information, where type is required
     * @return ResponseResult
     **/
    public Result get(ConversationModel conversation) throws Exception {
        String message = CommonUtil.checkFiled(conversation,PATH,CheckMethod.GET);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode(conversation.type, UTF8));
        sb.append("&requestId=").append(URLEncoder.encode(conversation.userId, UTF8));
        sb.append("&targetId=").append(URLEncoder.encode(conversation.targetId, UTF8));
        if(conversation.busChannel != null){
            sb.append("&busChannel=").append(URLEncoder.encode(conversation.getBusChannel(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/conversation/notification/get.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ConversationNotificationResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GET,HttpUtil.returnResult(conn, rongCloud.getConfig())), ConversationNotificationResult.class);
    }


    /**
     * Set conversation top
     */
    public ResponseResult setTop(ConversationSetTopModel model) throws Exception {
        String method = CheckMethod.SET_TOP;
        ResponseResult result = checkFiled(model, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", model.getUserId());
        addFormParam(sb, "&conversationType=", model.getConversationType());
        addFormParam(sb, "&targetId=", model.getTargetId());
        addFormParam(sb, "&setTop=", model.getSetTop());
        String body = sb.toString();

        return doRequest("/conversation/top/set.json", body, method, ResponseResult.class);
    }

    private ResponseResult required(String fieldName) {
        return new ResponseResult(PARAM_ERROR, String.format(ERR_REQUIRED_TEMPLATE, fieldName));
    }

    /**
     * Set User Conversation Tags.
     */
    public ResponseResult setUserConversationTag(UserConversationTagModel model) throws Exception {
        if (model == null){
            return required("model");
        }
        if (StringUtils.isBlank(model.getUserId())) {
            return required("userId");
        }
        if (model.getTags() == null || model.getTags().isEmpty()) {
            return required("tags");
        }

        List<ConversationTagModel> createTagList = new ArrayList<>();
        for (ConversationTagModel tag : model.getTags()) {
            if (StringUtils.isBlank(tag.getTagId())) {
                return required("tagId");
            }
            if (StringUtils.isBlank(tag.getTagName())) {
                return required("tagName");
            }
            createTagList.add(new ConversationTagModel(tag.getTagId(), tag.getTagName()));
        }
        UserConversationTagModel param = new UserConversationTagModel(model.getUserId(), createTagList);
        return doRequest("/user/conversation/tag/set.json", GsonUtil.toJson(param, UserConversationTagModel.class),
                "", ResponseResult.class, CONTENT_TYPE_JSON);
    }


    /**
     * Delete User Conversation Tags.
     */
    public ResponseResult deleteUserConversationTag(String userId, List<String> tagIds) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return required("userId");
        }
        if (tagIds == null || tagIds.isEmpty()) {
            return required("tagIds");
        }

        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("tagIds", tagIds);
        return doRequest("/user/conversation/tag/del.json", GsonUtil.toJson(param, Map.class),
                "", ResponseResult.class, CONTENT_TYPE_JSON);
    }

    /**
     * Query User Conversation Tags.
     */
    public ResultData<UserConversationTagModel> queryUserConversationTags(String userId) throws Exception{
        if (StringUtils.isBlank(userId)) {
            return new ResultData<>(PARAM_ERROR, String.format(ERR_REQUIRED_TEMPLATE, "userId"));
        }
        String uri = "/user/conversation/tag/get.json?userId=" + URLEncoder.encode(userId, UTF8);
        return doGetRequest(uri, ResultData.class);
    }


    /**
     * Set tag to conversations
     */
    public ResponseResult setConversationTag(TagConversationsModel tagConversations) throws Exception {
        return setOrDeleteConversationTag(tagConversations,false);
    }

    /**
     * Remove tag from conversations
     */
    public ResponseResult removeConversationTag(TagConversationsModel tagConversations) throws Exception {
        return setOrDeleteConversationTag(tagConversations,true);
    }


    private ResponseResult setOrDeleteConversationTag(TagConversationsModel tagConversations, boolean delete) throws Exception {
        if (tagConversations == null) {
            return required("tagConversations");
        }
        if (StringUtils.isBlank(tagConversations.getUserId())) {
            return required("userId");
        }
        if (StringUtils.isBlank(tagConversations.getTagId())) {
            return required("tagId");
        }
        if (tagConversations.getConversations() == null || tagConversations.getConversations().isEmpty()) {
            return required("conversations");
        }
        for (TagConversationsModel.Conversation conversation : tagConversations.getConversations()) {
            if (StringUtils.isBlank(conversation.getTargetId())) {
                return required("targetId");
            }
            if (conversation.getConversationType() == null) {
                return required("conversationType");
            }
        }
        String uri = delete ? "/tag/conversation/del.json" : "/tag/conversation/set.json";
        return doRequest(uri, GsonUtil.toJson(tagConversations, TagConversationsModel.class),
                "", ResponseResult.class, CONTENT_TYPE_JSON);
    }


    /**
     * Query Conversations By Tag.
     */
    public ResultData<TagConversationsModel> queryTagConversations(String userId, String tagId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return new ResultData<>(PARAM_ERROR, String.format(ERR_REQUIRED_TEMPLATE, "userId"));
        }
        if (StringUtils.isBlank(tagId)) {
            return new ResultData<>(PARAM_ERROR, String.format(ERR_REQUIRED_TEMPLATE, "tagId"));
        }
        String uri = "/tag/conversation/get.json?userId=" + URLEncoder.encode(userId, UTF8) + "&tagId=" + URLEncoder.encode(tagId, UTF8);
        return doGetRequest(uri, ResultData.class);
    }


    /**
     * Query Conversation Attribute.
     */
    public ResultData<ConversationAttrModel> queryConversationAttribute(String userId, String targetId, Integer conversationType) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return new ResultData<>(PARAM_ERROR, String.format(ERR_REQUIRED_TEMPLATE, "userId"));
        }
        if (StringUtils.isBlank(targetId)) {
            return new ResultData<>(PARAM_ERROR, String.format(ERR_REQUIRED_TEMPLATE, "targetId"));
        }
        if (conversationType == null){
            return new ResultData<>(PARAM_ERROR, String.format(ERR_REQUIRED_TEMPLATE, "conversationType"));
        }
        String uri = "/conversation/attribute/get.json?userId=" + URLEncoder.encode(userId, UTF8) + "&targetId=" + URLEncoder.encode(targetId, UTF8) + "&conversationType=" + conversationType;
        return doGetRequest(uri, ResultData.class);
    }



}
