package io.rong.models.chatroom;

import io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class ChatroomDestroyTypeModel {
    /**
     * 聊天室用户Id。
     * */
    public String id;
    /**
     * 指定聊天室的销毁方式。
     * */
    public Integer destroyType;
    /**
     * 设置聊天室销毁时间。
     * */
    public Integer destroyTime;

    public ChatroomDestroyTypeModel() {
        super();
    }
    public ChatroomDestroyTypeModel(String id, Integer destroyType, Integer destroyTime) {
        this.id = id;
        this.destroyType = destroyType;
        this.destroyTime = destroyTime;
    }

    public String getId() {
        return id;
    }

    public ChatroomDestroyTypeModel setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getDestroyType() {
        return destroyType;
    }

    public ChatroomDestroyTypeModel setDestroyType(Integer destroyType) {
        this.destroyType = destroyType;
        return this;
    }

    public Integer getDestroyTime() {
        return destroyTime;
    }

    public ChatroomDestroyTypeModel setDestroyTime(Integer destroyTime) {
        this.destroyTime = destroyTime;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ChatroomDestroyTypeModel.class);
    }

}
