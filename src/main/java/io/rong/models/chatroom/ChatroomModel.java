package io.rong.models.chatroom;

import io.rong.util.GsonUtil;

/**
 * 聊天室。
 * @author RongCloud
 */
public class ChatroomModel {
	/**
	 * 聊天室 id。
	 */
	String id;
	/**
	 * 聊天室名。
	 */
	String name;
	/**
	 * 聊天室创建时间。
	 */
	String time;
	/**
	 * 聊天室成员。
	 */
	ChatroomMember[] members;
	/**
	 * 聊天室成员数。
	 */
	Integer count;
	/**
	 * 加入聊天室的先后顺序,1正序，2倒叙。
	 */
	Integer order;

	/**
	 * 禁言时间
	 * */
	Integer minute;

	public ChatroomModel() {
		super();
	}
	/**
	 * 聊天室构造函数 全量
	 * */
	public ChatroomModel(String id, String name, String time, ChatroomMember[] members,
						 Integer count, Integer order, Integer minute) {
		this.id = id;
		this.name = name;
		this.time = time;
		this.members = members;
		this.count = count;
		this.order = order;
		this.minute = minute;
	}

	/**
	 * 设置chrmId
	 *
	 */	
	public ChatroomModel setId(String id) {
		this.id = id;
		return this;
	}
	
	/**
	 * 获取chrmId
	 *
	 * @return String
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 设置name
	 *
	 */	
	public ChatroomModel setName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * 获取name
	 *
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置time
	 *
	 */	
	public ChatroomModel setTime(String time) {
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

	/**
	 * 获取memberIds
	 *
	 * @return String
	 */
	public ChatroomMember[] getMembers() {
		return this.members;
	}
	/**
	 * 设置memberIds
	 *
	 * @return String
	 */
	public ChatroomModel setMembers(ChatroomMember[] members) {
		this.members = members;
		return this;
	}

	/**
	 * 设置count
	 *
	 * @return String
	 */
	public Integer getCount() {
		return this.count;
	}
	/**
	 * 获取count
	 *
	 * @return String
	 */
	public ChatroomModel setCount(Integer count) {
		this.count = count;
		return this;
	}
	/**
	 * 设置order
	 *
	 * @return String
	 */
	public Integer getOrder() {
		return this.order;
	}
	/**
	 * 获取order
	 *
	 * @return String
	 */
	public ChatroomModel setOrder(Integer order) {
		this.order = order;
		return this;
	}
	/**
	 * 获取minute
	 *
	 * @return String
	 */
	public Integer getMinute() {
		return this.minute;
	}
	/**
	 * 设置minute
	 *
	 * @return String
	 */
	public ChatroomModel setMinute(Integer minute) {
		this.minute = minute;
		return this;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatroomModel.class);
	}
}
