package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 * 已读通知类型的消息
 * 
 */
public class ReadReceiptMessage extends BaseMessage {

	/**
	 * 最后一条消息的发送时间 - 必传 如: 1589437637920
	 */
	private String lastMessageSendTime = "";
	
	/**
	 *  最后一条消息的消息Id
	 */
	private String messageUId = "";
	
	/**
	 * 消息类型 参见:CodeUtil.ConversationType 类
	 */
	private int type = 1;

	private transient static final String TYPE = "RC:ReadNtf";
	
	public ReadReceiptMessage() {
		super();
	}

	public ReadReceiptMessage(String lastMessageSendTime, String messageUId, int type) {
		super();
		this.lastMessageSendTime = lastMessageSendTime;
		this.messageUId = messageUId;
		this.type = type;
	}

	public String getLastMessageSendTime() {
		return lastMessageSendTime;
	}

	public void setLastMessageSendTime(String lastMessageSendTime) {
		this.lastMessageSendTime = lastMessageSendTime;
	}

	public String getMessageUId() {
		return messageUId;
	}

	public void setMessageUId(String messageUId) {
		this.messageUId = messageUId;
	}

	public int getMessageType() {
		return type;
	}

	public void setMessageType(int type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ReadReceiptMessage.class);
	}

}
