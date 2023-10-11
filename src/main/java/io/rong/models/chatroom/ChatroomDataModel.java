package io.rong.models.chatroom;

import io.rong.util.GsonUtil;
import java.util.HashMap;

/**
 * 聊天室实体 - v2
 *
 * @author RongCloud
 */
public class ChatroomDataModel {

    /**
     * 聊天室 id。
     */
    String id;
    /**
     * 指定聊天室的销毁类型。
     */
    Integer destroyType;
    /**
     * 设置聊天室销毁时间。
     */
    Integer destroyTime;
    /**
     * 是否禁言聊天室全体成员。
     */
    Boolean isBan;
    /**
     * 聊天室成员。
     */
    String[] whiteUserIds;

    /**
     * 聊天室自定义属性的所属用户 ID。
     */
    String entryOwnerId;

    /**
     * 聊天室自定义属性 KV 对
     */
    HashMap<String, String> entryInfo;

    public ChatroomDataModel() {
        super();
    }

    /**
     * 聊天室构造函数 全量
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
