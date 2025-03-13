package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * Status Message Body
 * 
 * @author RongCloud
 */
public class PrivateStatusMessage {

	/**
	 * The sender's user ID. (Required)
	 */
	private String senderId;

	/**
	 * The recipient user IDs. You can send messages to multiple users, with a maximum of 1000 users per request. (Required)
	 */
	private String[] targetId;

	/**
	 * The message type. Refer to the RongCloud message type table. Custom message types should not start with "RC:" to avoid conflicts with built-in message types. (Required)
	 */
	private String objectName;

	/**
	 * The message content. The maximum size for a single message is 128k. Refer to the RongCloud message type table for examples. Custom message types can have a custom format. (Required)
	 */
	private BaseMessage content;

	/**
	 * Whether to filter the sender's blocklist. 0 means no filtering, 1 means filtering. Default is 0. (Optional)
	 */
	private int verifyBlacklist = 0;

	/**
	 * Whether the sender should receive the message. 0 means the sender does not receive the message, 1 means the sender receives the message. Default is 0. (Optional)
	 */
	private int isIncludeSender = 0;

	private Long msgRandom;

	public String getSenderId() {
		return senderId;
	}

	public PrivateStatusMessage setSenderId(String senderId) {
		this.senderId = senderId;
		return this;
	}

	public String[] getTargetId() {
		return targetId;
	}

	public PrivateStatusMessage setTargetId(String[] targetId) {
		this.targetId = targetId;
		return this;
	}

	public String getObjectName() {
		return this.objectName;
	}

	public PrivateStatusMessage setObjectName(String objectName) {
		this.objectName = objectName;
		return this;
	}

	public BaseMessage getContent() {
		return content;
	}

	public PrivateStatusMessage setContent(BaseMessage content) {
		this.content = content;
		return this;
	}

	public int getVerifyBlacklist() {
		return verifyBlacklist;
	}

	public PrivateStatusMessage setVerifyBlacklist(int verifyBlacklist) {
		this.verifyBlacklist = verifyBlacklist;
		return this;
	}

	public int getIsIncludeSender() {
		return isIncludeSender;
	}

	public PrivateStatusMessage setIsIncludeSender(int isIncludeSender) {
		this.isIncludeSender = isIncludeSender;
		return this;
	}

	public Long getMsgRandom() {
		return msgRandom;
	}

	public PrivateStatusMessage setMsgRandom(Long msgRandom) {
		this.msgRandom = msgRandom;
		return this;
	}
}
