package io.rong.models.message;

import io.rong.util.GsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rongcloud
 */
public class MentionMessage {

    public String senderId;
    /**
     * 接收群 Id，提供多个本参数可以实现向多群发送消息，最多不超过 3 个群组。（必传）
     */
    public String[] targetId;
    public String objectName;
    /**
     * 消息 内容
     */
    public MentionMessageContent content;
    public String pushContent;
    public String pushData;
    public String pushExt;
    public Integer isPersisted;
    public Integer isCounted;
    public Integer isIncludeSender;
    private Integer contentAvailable;
    private Long msgRandom;
    /**
     * 是否为可扩展消息，默认为 false，设为 true 时终端在收到该条消息后，可对该条消息设置扩展信息（可选）。暂不支持海外数据中心
     */
    public Boolean expansion;

    /**
     * 扩展消息内容，expansion 为true 的时候生效
     */
    public Map<String, String> extraContent;


    /**
     * 禁止更新会话最后一条消息。 当该参数为 false 时，发送的该条消息都会进入会话列表; 为 true 时，不会更新到会话列表的消息内容。
     * 注：此参数仅对存储在客户端的消息有效。
     */
    private Boolean disableUpdateLastMsg;

    public MentionMessage() {
    }

    public MentionMessage(String senderId, String[] targetId, String objectName, MentionMessageContent content,
        String pushContent, String pushData,
        Integer isPersisted, Integer isCounted, Integer isIncludeSender, Integer contentAvailable) {
        this.senderId = senderId;
        this.targetId = targetId;
        this.objectName = objectName;
        this.content = content;
        this.pushContent = pushContent;
        this.pushData = pushData;
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.isIncludeSender = isIncludeSender;
        this.contentAvailable = contentAvailable;
    }

    public MentionMessage(String senderId, String[] targetId, String objectName, MentionMessageContent content,
        String pushContent, String pushData, String pushExt, Integer isPersisted, Integer isCounted,
        Integer isIncludeSender, Integer contentAvailable) {
        this.senderId = senderId;
        this.targetId = targetId;
        this.objectName = objectName;
        this.content = content;
        this.pushContent = pushContent;
        this.pushData = pushData;
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.isIncludeSender = isIncludeSender;
        this.contentAvailable = contentAvailable;
        this.pushExt = pushExt;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public MentionMessage setSenderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    public String[] getTargetId() {
        return this.targetId;
    }

    public MentionMessage setTargetId(String[] targetId) {
        this.targetId = targetId;
        return this;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public MentionMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public MentionMessageContent getContent() {
        return this.content;
    }

    public MentionMessage setContent(MentionMessageContent content) {
        this.content = content;
        return this;
    }

    public String getPushContent() {
        return this.pushContent;
    }

    public MentionMessage setPushContent(String pushContent) {
        this.pushContent = pushContent;
        return this;
    }

    public String getPushData() {
        return this.pushData;
    }

    public MentionMessage setPushData(String pushData) {
        this.pushData = pushData;
        return this;
    }

    public String getPushExt() {
        return this.pushExt;
    }

    public MentionMessage setPushExt(String pushExt) {
        this.pushExt = pushExt;
        return this;
    }

    public MentionMessage setPushExt(String title, Integer forceShowPushContent, Platform... platforms) {
        PushExt pe = new PushExt();
        pe.setTitle(title);
        if (forceShowPushContent != null) {
            pe.setForceShowPushContent(forceShowPushContent);
        }
        List<Platform> pushConfigs = new ArrayList<Platform>();
        for (Platform p : platforms) {
            pushConfigs.add(p);
        }
        pe.setPushConfigs(pushConfigs);
        this.pushExt = GsonUtil.toJson(pe, PushExt.class);
        return this;
    }

    public Integer getIsPersisted() {
        return this.isPersisted;
    }

    public MentionMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    public Integer getIsCounted() {
        return this.isCounted;
    }

    public MentionMessage setIsCounted(Integer isCounted) {
        this.isCounted = isCounted;
        return this;
    }

    public Integer getIsIncludeSender() {
        return this.isIncludeSender;
    }

    public MentionMessage setIsIncludeSender(Integer isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
        return this;
    }

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public MentionMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }

    public Long getMsgRandom() {
        return msgRandom;
    }

    public MentionMessage setMsgRandom(Long msgRandom) {
        this.msgRandom = msgRandom;
        return this;
    }

    public Boolean getExpansion() {
        return expansion;
    }

    public MentionMessage setExpansion(Boolean expansion) {
        this.expansion = expansion;
        return this;
    }

    public Map<String, String> getExtraContent() {
        return extraContent;
    }

    public MentionMessage setExtraContent(Map<String, String> extraContent) {
        this.extraContent = extraContent;
        return this;
    }

    public Boolean getDisableUpdateLastMsg() {
        return disableUpdateLastMsg;
    }

    public MentionMessage setDisableUpdateLastMsg(Boolean disableUpdateLastMsg) {
        this.disableUpdateLastMsg = disableUpdateLastMsg;
        return this;
    }
}
