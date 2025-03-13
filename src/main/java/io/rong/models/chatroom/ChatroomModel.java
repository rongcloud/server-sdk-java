package io.rong.models.chatroom;

import io.rong.util.GsonUtil;

/**
 * Chatroom.
 * @author RongCloud
 */
public class ChatroomModel {
	/**
	 * Chatroom ID.
	 */
	String id;
	/**
	 * Chatroom name.
	 */
	String name;
	/**
	 * Chatroom creation time.
	 */
	String time;
	/**
	 * Chatroom members.
	 */
	ChatroomMember[] members;
	/**
	 * Number of chatroom members.
	 */
	Integer count;
	/**
	 * Order of joining the chatroom, 1 for ascending, 2 for descending.
	 */
	Integer order;

	/**
	 * Mute duration in minutes.
	 * */
	Integer minute;

	String extra;

	Boolean needNotify;

	public ChatroomModel() {
		super();
	}
	/**
	 * Full constructor for Chatroom.
	 * */
	public ChatroomModel(String id, String name, String time, ChatroomMember[] members,
						 Integer count, Integer order, Integer minute,String extra,Boolean needNotify) {
		this.id = id;
		this.name = name;
		this.time = time;
		this.members = members;
		this.count = count;
		this.order = order;
		this.minute = minute;
		this.extra = extra;
		this.needNotify = needNotify;
	}

	/**
	 * Sets the chatroom ID
	 *
	 */	
	public ChatroomModel setId(String id) {
		this.id = id;
		return this;
	}
	
	/**
	 * Gets the chatroom ID
	 *
	 * @return String
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the name
	 *
	 */	
	public ChatroomModel setName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * Gets the name
	 *
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the time
	 *
	 */
	public ChatroomModel setTime(String time) {
		this.time = time;
		return this;
	}
	
	/**
	 * Get time
	 *
	 * @return String
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Get member IDs
	 *
	 * @return String
	 */
	public ChatroomMember[] getMembers() {
		return this.members;
	}
	/**
	 * Set member IDs
	 *
	 * @return String
	 */
	public ChatroomModel setMembers(ChatroomMember[] members) {
		this.members = members;
		return this;
	}

	/**
	 * Set count
	 *
	 * @return String
	 */
	public Integer getCount() {
		return this.count;
	}
	/**
	 * Get count
	 *
	 * @return String
	 */
	public ChatroomModel setCount(Integer count) {
		this.count = count;
		return this;
	}
	/**
	 * Sets the order.
	 *
	 * @return String
	 */
	public Integer getOrder() {
		return this.order;
	}
	/**
	 * Gets the order.
	 *
	 * @return String
	 */
	public ChatroomModel setOrder(Integer order) {
		this.order = order;
		return this;
	}
	/**
	 * Gets the minute.
	 *
	 * @return String
	 */
	public Integer getMinute() {
		return this.minute;
	}
	/**
	 * Sets the minute.
	 *
	 * @return String
	 */
	public ChatroomModel setMinute(Integer minute) {
		this.minute = minute;
		return this;
	}

	public String getExtra() {
		return extra;
	}

	public ChatroomModel setExtra(String extra) {
		this.extra = extra;
		return this;
	}

	public Boolean getNeedNotify() {
		return needNotify;
	}

	public ChatroomModel setNeedNotify(Boolean needNotify) {
		this.needNotify = needNotify;
		return this;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatroomModel.class);
	}
}
