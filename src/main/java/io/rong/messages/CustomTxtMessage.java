package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * Custom message
 *
 */
public class CustomTxtMessage extends BaseMessage {
	private String content = "";
	private transient static final String TYPE = "RC:TxtMsg";

	public CustomTxtMessage(String content) {
		this.content = content;
	}
	@Override
	public String getType() {
		return TYPE;
	}
	
	/**
	 * Retrieves the content of the custom message.
	 *
	 * @return String
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @param content Sets the content of the custom message.
	 *
	 *
	 */
	public void setContent(String content) {
		this.content = content;
	}  
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, CustomTxtMessage.class);
	}
}