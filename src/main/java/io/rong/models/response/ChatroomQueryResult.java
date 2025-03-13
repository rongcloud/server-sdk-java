package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * Chatroom query result.
 */
public class ChatroomQueryResult extends Result {

    /**
     * The ID of the chatroom.
     */
    String chatroomId;
    /**
     * The creation time of the chatroom.
     */
    Long createTime;
    /**
     * The current number of members in the chatroom.
     */
    Integer memberCount;
    /**
     * Specifies the destruction type of the chatroom.
     */
    Integer destroyType;
    /**
     * Sets the waiting time for chatroom destruction.
     */
    Integer destroyTime;
    /**
     * Indicates whether the chatroom is in a mute-all state.
     */
    Boolean ban;

	public String getChatroomId() {
		return chatroomId;
	}

	public void setChatroomId(String chatroomId) {
		this.chatroomId = chatroomId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public Integer getDestroyType() {
		return destroyType;
	}

	public void setDestroyType(Integer destroyType) {
		this.destroyType = destroyType;
	}

	public Integer getDestroyTime() {
		return destroyTime;
	}

	public void setDestroyTime(Integer destroyTime) {
		this.destroyTime = destroyTime;
	}

	public Boolean getBan() {
		return ban;
	}

	public void setBan(Boolean ban) {
		this.ban = ban;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatroomQueryResult.class);
	}
}
