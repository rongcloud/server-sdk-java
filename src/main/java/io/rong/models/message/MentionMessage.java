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
     * Target group IDs. You can send messages to multiple groups by providing multiple IDs, with a maximum of 3 groups. (Required)
     */
    public String[] targetId;
    public String[] toUserId;
    public String objectName;
    /**
     * Message content
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
     * Indicates whether the message is extensible. Default is false. When set to true, the client can set extended information for this message after receiving it. (Optional) Not supported in global data centers.
     */
    public Boolean expansion;

    /**
     * Indicates whether the message is silent. Default is false. When set to true, offline users will not receive notification reminders. Only valid when a single group ID is provided in toGroupId.
     */
    public Boolean disablePush;

    /**
     * Extended message content, effective when expansion is set to true.
     */
    public Map<String, String> extraContent;


    /**
     * Disables updating the last message in the conversation. When this parameter is set to false, the sent message will appear in the conversation list; when set to true, the message content will not be updated in the conversation list.
     * Note: This parameter only applies to messages stored on the client side.
     */
    private Boolean disableUpdateLastMsg;

    /**
     * Whether the message need readReceipt; 0 means not needed, 1 means need. Default is 0.
     */
    public Integer needReadReceipt;

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

    public String[] getToUserId() {
        return toUserId;
    }

    public MentionMessage setToUserId(String[] toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    public Boolean getDisablePush() {
        return disablePush;
    }

    public MentionMessage setDisablePush(Boolean disablePush) {
        this.disablePush = disablePush;
        return this;
    }

    public Integer getNeedReadReceipt() {
        return needReadReceipt;
    }

    public MentionMessage setNeedReadReceipt(Integer needReadReceipt) {
        this.needReadReceipt = needReadReceipt;
        return this;
    }
}
