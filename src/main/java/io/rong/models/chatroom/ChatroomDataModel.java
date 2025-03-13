package io.rong.models.chatroom;

import io.rong.util.GsonUtil;
import java.util.HashMap;

/**
 * Chatroom entity - v2
 *
 * @author RongCloud
 */
public class ChatroomDataModel {

    /**
     * The ID of the chatroom.
     */
    String id;
    /**
     * Specifies the destruction type of the chatroom.
     */
    Integer destroyType;
    /**
     * Sets the destruction time of the chatroom.
     */
    Integer destroyTime;
    /**
     * Indicates whether all members of the chatroom are muted.
     */
    Boolean isBan;
    /**
     * Members of the chatroom.
     */
    String[] whiteUserIds;

    /**
     * The user ID to which the chatroom custom attributes belong.
     */
    String entryOwnerId;

    /**
     * Key-value pairs of chatroom custom attributes.
     */
    HashMap<String, String> entryInfo;

    public ChatroomDataModel() {
        super();
    }

    /**
     * Full constructor for the chatroom.
     */
    public ChatroomDataModel(String id, Integer destroyType, Integer destroyTime, Boolean isBan, String[] whiteUserIds,
      String entryOwnerId, HashMap<String, String> entryInfo) {
        this.id = id;
        this.destroyType = destroyType;
        this.destroyTime = destroyTime;
        this.isBan = isBan;
        this.whiteUserIds = whiteUserIds;
        this.entryOwnerId = entryOwnerId;
        this.entryInfo = entryInfo;
    }

    public String getId() {
        return id;
    }

    public ChatroomDataModel setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getDestroyType() {
        return destroyType;
    }

    public ChatroomDataModel setDestroyType(Integer destroyType) {
        this.destroyType = destroyType;
        return this;
    }

    public Integer getDestroyTime() {
        return destroyTime;
    }

    public ChatroomDataModel setDestroyTime(Integer destroyTime) {
        this.destroyTime = destroyTime;
        return this;
    }

    public Boolean getIsBan() {
        return isBan;
    }

    public ChatroomDataModel setIsBan(Boolean isBan) {
        this.isBan = isBan;
        return this;
    }

    public String[] getWhiteUserIds() {
        return whiteUserIds;
    }

    public ChatroomDataModel setWhiteUserIds(String[] whiteUserIds) {
        this.whiteUserIds = whiteUserIds;
        return this;
    }

    public String getEntryOwnerId() {
        return entryOwnerId;
    }

    public ChatroomDataModel setEntryOwnerId(String entryOwnerId) {
        this.entryOwnerId = entryOwnerId;
        return this;
    }

    public HashMap<String, String> getEntryInfo() {
        return entryInfo;
    }

    public ChatroomDataModel setEntryInfo(HashMap<String, String> entryInfo) {
        this.entryInfo = entryInfo;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ChatroomDataModel.class);
    }
}
