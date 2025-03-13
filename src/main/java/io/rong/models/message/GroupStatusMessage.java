package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * Group status information
 * 
 * @author RongCloud
 *
 */
public class GroupStatusMessage {
	/**
	 * The sender's user ID. (Required)
	 */
	private String senderId;

	/**
	 * The target group IDs. You can send messages to up to 3 groups by providing multiple group IDs. (Required)
	 */
	private String[] groupId;

	/**
	 * The message type, refer to RongCloud message type table. The message flag; you can customize the message type, but ensure it does not exceed 32 characters and does not start with "RC:" to avoid conflicts with RongCloud's built-in message ObjectName. (Required)
	 */
	private String objectName;

	/**
	 * The message content, with a maximum size of 128k per message. Refer to RongCloud message type table for examples. If the objectName is a custom message type, the content can be in a custom format. (Required)
	 */
	private BaseMessage content;

	/**
	 * Whether to filter the sender's blocklist. 0 means no filtering, 1 means filtering. Default is 0 (no filtering). (Optional)
	 */
	private int verifyBlacklist = 0;

	/**
	 * Whether the sender should receive the message. 0 means the sender does not receive the message, 1 means the sender receives the message. Default is 0 (sender does not receive the message). (Optional)
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
