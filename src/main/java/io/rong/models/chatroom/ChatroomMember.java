package io.rong.models.chatroom;

import io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class ChatroomMember {
    /**
     * 聊天室用户Id。
     * */
    public String id;
    /**
     * 加入聊天室时间。
     * */
    public String time;
    /**
     * 聊天室ID
     * */
    public String chatroomId;

    public Integer munite;

    public ChatroomMember() {
        super();
    }
    public ChatroomMember(String id, String time) {
        this.id = id;
        this.time = time;
    }

    public ChatroomMember(String id, String chatroomId, String time) {
        this.id = id;
        this.chatroomId = chatroomId;
        this.time = time;
    }

    /**
     * 设置id
     *
     */
    public ChatroomMember setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 获取id
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * 设置time
     *
     */
    public ChatroomMember setTime(String time) {
        this.time = time;
        return this;
    }

    /**
     * 获取time
     *
     * @return String
     */
    public String getTime() {
        return time;
    }

    public String getChatroomId() {
        return this.chatroomId;
    }

    public ChatroomMember setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
        return this;
    }
    /**
     * 获取禁言时长
     *
     * @return String
     */
    public Integer getMunite() {
        return this.munite;
    }
    /**
     * 设置munite
     *
     *
     */
    public void setMunite(Integer munite) {
        this.munite = munite;
    }
    @Override
    public String toString() {
        return GsonUtil.toJson(this, ChatroomMember.class);
    }

}
