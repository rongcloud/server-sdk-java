package io.rong.models.chatroom;

import io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class ChatroomMember {
    /**
     * Chatroom user ID.
     * */
    public String id;
    /**
     * Time when the user joined the chatroom.
     * */
    public String time;
    /**
     * Chatroom ID.
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
     * Set the user ID.
     *
     */
    public ChatroomMember setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the user ID.
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Set the time
     *
     */
    public ChatroomMember setTime(String time) {
        this.time = time;
        return this;
    }

    /**
     * Get the time
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
     * Get the mute duration
     *
     * @return String
     */
    public Integer getMunite() {
        return this.munite;
    }
    /**
     * Set the munite
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
