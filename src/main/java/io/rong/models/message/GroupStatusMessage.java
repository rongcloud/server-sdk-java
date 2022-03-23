package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * 群组状态信息
 * 
 * @author RongCloud
 *
 */
public class GroupStatusMessage {
	/**
	 * 发送人用户 Id。（必传）
	 */
	private String senderId;

	/**
	 * 接收群Id，提供多个本参数可以实现向多群发送消息，最多不超过 3 个群组。（必传）
	 */
	private String[] groupId;

	/**
	 * 消息类型，参考融云消息类型表.消息标志；可自定义消息类型，长度不超过 32 个字符，您在自定义消息时需要注意，不要以 "RC:"
	 * 开头，以避免与融云系统内置消息的 ObjectName 重名。（必传）
	 */
	private String objectName;

	/**
	 * 发送消息内容，单条消息最大 128k，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）
	 */
	private BaseMessage content;

	/**
	 * 是否过滤发送人黑名单列表，0 表示为不过滤、 1 表示为过滤，默认为 0 不过滤。(可选)
	 */
	private int verifyBlacklist = 0;

	/**
	 * 发送用户自己是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。（可选）
	 */
	private int isIncludeSender = 0;

	private Long msgRandom;

	
	public String getSenderId() {
		return senderId;
	}

	public GroupStatusMessage setSenderId(String senderId) {
		this.senderId = senderId;
		return this;
	}

	public String[] getGroupId() {
		return groupId;
	}

	public GroupStatusMessage setGroupId(String[] groupId) {
		this.groupId = groupId;
		return this;
	}

	public String getObjectName() {
		return objectName;
	}

	public GroupStatusMessage setObjectName(String objectName) {
		this.objectName = objectName;
		return this;
	}

	public BaseMessage getContent() {
		return content;
	}

	public GroupStatusMessage setContent(BaseMessage content) {
		this.content = content;
		return this;
	}

	public int getVerifyBlacklist() {
		return verifyBlacklist;
	}

	public GroupStatusMessage setVerifyBlacklist(int verifyBlacklist) {
		this.verifyBlacklist = verifyBlacklist;
		return this;
	}

	public int getIsIncludeSender() {
		return isIncludeSender;
	}

	public GroupStatusMessage setIsIncludeSender(int isIncludeSender) {
		this.isIncludeSender = isIncludeSender;
		return this;
	}

	public Long getMsgRandom() {
		return msgRandom;
	}

	public GroupStatusMessage setMsgRandom(Long msgRandom) {
		this.msgRandom = msgRandom;
		return this;
	}
}
