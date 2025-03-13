package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 * A message type for read receipt notification.
 */
public class ReadReceiptMessage extends BaseMessage {

	/**
	 * The send time of the last message - Required. Example: 1589437637920
	 */
	private String lastMessageSendTime = "";
	
	/**
	 * The Message UID of the last message.
	 */
	private String messageUId = "";
	
	/**
	 * The message type. Refer to: CodeUtil.ConversationType class.
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
