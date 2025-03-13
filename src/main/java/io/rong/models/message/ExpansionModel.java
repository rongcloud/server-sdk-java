package io.rong.models.message;

import io.rong.models.user.UserModel;
import io.rong.util.GsonUtil;

import java.util.HashMap;
import java.util.Set;

/**
 * Message extension information
 */
public class ExpansionModel {

    /**
     * The unique identifier of the message. The server can obtain this through the Post-messaging Callback feature.
     */
    public String msgUID;
    /**
     * The user ID of the sender who needs to set the message extension.
     */
    public String userId;
    /**
     * The conversation type, where 1 represents a one-to-one chat and 3 represents a group chat. Only one-to-one and group chat types are supported.
     */
    public Integer conversationType;
    /**
     * The target ID, which could be a user ID or group ID depending on the conversation type.
     */
    private String targetId;
    /**
     * The custom extension content of the message, in JSON format, set as key-value pairs, e.g., {"type":"3"}. A single message can have up to 300 extensions, and a maximum of 100 extensions can be set at once.
     */
    private HashMap<String, String> extraKeyVal;
    /**
     * The keys of the extensions to be deleted. A maximum of 100 extensions can be deleted at once.
     */
    private Set extraKey;

    /**
     * Whether the sender should receive the setting status when the end user is online. 0 indicates not to receive, 1 indicates to receive. Default is 0 (not receive).
     */
    private int isSyncSender = 0;

    /**
     * The message channel ID.
     */
    private String busChannel;

    public ExpansionModel() {
    }

    public ExpansionModel(String msgUID, Integer conversationType, String userId, String targetId) {
        this.msgUID = msgUID;
        this.conversationType = conversationType;
        this.userId = userId;
        this.targetId = targetId;
    }

    public ExpansionModel(String msgUID, String userId, String targetId, HashMap<String, String> extraKeyVal, String busChannel) {
        this.msgUID = msgUID;
        this.userId = userId;
        this.targetId = targetId;
        this.extraKeyVal = extraKeyVal;
        this.busChannel = busChannel;
    }

    public ExpansionModel(String msgUID, String userId, Integer conversationType, String targetId, Set extraKey, String busChannel) {
        this.msgUID = msgUID;
        this.userId = userId;
        this.conversationType = conversationType;
        this.targetId = targetId;
        this.extraKey = extraKey;
        this.busChannel = busChannel;
    }

    public String getMsgUID() {
        return msgUID;
    }

    public void setMsgUID(String msgUID) {
        this.msgUID = msgUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getConversationType() {
        return conversationType;
    }

    public void setConversationType(Integer conversationType) {
        this.conversationType = conversationType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public HashMap<String, String> getExtraKeyVal() {
        return extraKeyVal;
    }

    public void setExtraKeyVal(HashMap<String, String> extraKeyVal) {
        this.extraKeyVal = extraKeyVal;
    }

    public Set getExtraKey() {
        return extraKey;
    }

    public void setExtraKey(Set extraKey) {
        this.extraKey = extraKey;
    }

    public int getIsSyncSender() {
        return isSyncSender;
    }

    public void setIsSyncSender(int isSyncSender) {
        this.isSyncSender = isSyncSender;
    }

    public String getBusChannel() {
        return busChannel;
    }

    public void setBusChannel(String busChannel) {
        this.busChannel = busChannel;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ExpansionModel.class);
    }
}
