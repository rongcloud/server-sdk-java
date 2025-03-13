package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * Gray bar notification message. This type of message does not have a push notification.
 *
 */
public class InfoNtfMessage extends BaseMessage {
	private String message = "";
	private String extra = "";
	private transient static final String TYPE = "RC:InfoNtf";
	
	public InfoNtfMessage(String message, String extra) {
		this.message = message;
		this.extra = extra;
	}
	@Override
	public String getType() {
		return TYPE;
	}
	
	/**
	 * Gets the content of the gray bar message.
	 *
	 * @return String
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @param message Sets the content of the gray bar message.
	 *
	 *
	 */
	public void setMessage(String message) {
		this.message = message;
	}  
	
	/**
	 * Gets the extra information (if developers need it, they can parse it on the App side).
	 *
	 * @return String
	 */
	public String getExtra() {
		return extra;
	}

	/**
	 * @param extra Sets additional information (if needed, developers can parse it on the App side).
	 *
	 *
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, InfoNtfMessage.class);
	}
}