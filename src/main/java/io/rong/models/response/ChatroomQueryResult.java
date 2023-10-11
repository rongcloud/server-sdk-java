package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.util.GsonUtil;

import java.util.List;

/**
 *  chatroomQuery 返回结果
 */
public class ChatroomQueryResult extends Result {

	/**
	 * 聊天室 ID
	 */
	String chatroomId;
	/**
	 * 聊天室创建时间
	 */
	Long createTime;
	/**
	 * 聊天室当前人数
	 */
	Integer memberCount;
	/**
	 * 指定聊天室的销毁方式。
	 */
	Integer destroyType;
	/**
	 * 设置聊天室销毁等待时间。
	 */
	Integer destroyTime;
	/**
	 * 是否已开启聊天室全体禁言
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
