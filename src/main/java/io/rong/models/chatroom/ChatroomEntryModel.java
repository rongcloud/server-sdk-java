package io.rong.models.chatroom;

import io.rong.util.GsonUtil;
import java.util.HashMap;

/**
 * Chatroom attribute setting request model
 * <p>
 * 1. Set attribute 2. Delete attribute 3. Get attribute
 *
 * @author RongCloud
 */
public class ChatroomEntryModel {

    /**
     * Chatroom ID
     */
    public String chatroomId;

    /**
     * Operator user ID. Can be set via Server API by non-chatroom users.
     */
    public String userId;

    /**
     * Chatroom attribute name. Key supports uppercase/lowercase letters, numbers, and special symbols + = - _. Case-sensitive. Maximum length: 128 characters
     */
    public String key;

    /**
     * Collection of keys for querying chatroom attributes. Maximum 100 keys. If empty, retrieves all keys.
     */
    public String[] keys;

    /**
     * Chatroom attribute value. Maximum length: 4096 characters
     */
    public String value;

    /**
     * Optional
     * <p>
     * Whether to delete this key when the user exits the chatroom. 1: Delete; 0: Do not delete this key. Default: 0
     */
    public Integer autoDelete = 0;

    /**
     * Optional
     * <p>
     * Notification message type. Whether to send a notification message after setting the attribute. If needed, set to RC:chrmKVNotiMsg
     */
    public String objectName;

    /**
     * Optional
     * <p>
     * Notification message content, in JSON format. When objectName is RC:chrmKVNotiMsg, content must include type, key, and value
     * attributes. Refer to the RC:chrmKVNotiMsg structure for details.
     */
    public String content;

    /**
     * Batch setting - The user ID to which the chatroom custom attributes belong.
     */
    public String entryOwnerId;

    /**
     * Batch setting - Chatroom custom attributes KV.
     */
    public HashMap<String, String> entryInfo;

    public String getChatroomId() {
        return chatroomId;
    }

    public ChatroomEntryModel setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public ChatroomEntryModel setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getKey() {
        return key;
    }

    public ChatroomEntryModel setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ChatroomEntryModel setValue(String value) {
        this.value = value;
        return this;
    }

    public Integer isAutoDelete() {
        return autoDelete;
    }

    public ChatroomEntryModel setAutoDelete(Integer autoDelete) {
        this.autoDelete = autoDelete;
        return this;
    }

    public ChatroomEntryModel setAutoDelete(Boolean autoDelete) {
        this.autoDelete = autoDelete ? 1 : 0;
        return this;
    }

    public String getObjectName() {
        return objectName;
    }

    public ChatroomEntryModel setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ChatroomEntryModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String[] getKeys() {
        return keys;
    }

    public ChatroomEntryModel setKeys(String[] keys) {
        this.keys = keys;
        return this;
    }

    public String getEntryOwnerId() {
        return entryOwnerId;
    }

    public ChatroomEntryModel setEntryOwnerId(String entryOwnerId) {
        this.entryOwnerId = entryOwnerId;
        return this;
    }

    public HashMap<String, String> getEntryInfo() {
        return entryInfo;
    }

    public ChatroomEntryModel setEntryInfo(HashMap<String, String> entryInfo) {
        this.entryInfo = entryInfo;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ChatroomEntryModel.class);
    }
}
