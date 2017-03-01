package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * 提示条（小灰条）通知消息。此类型消息没有 Push 通知。
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
	
	public String getType() {
		return TYPE;
	}
	
	/**
	 * 获取提示条消息内容。
	 *
	 * @returnString
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 设置提示条消息内容。
	 *
	 * @return
	 */
	public void setMessage(String message) {
		this.message = message;
	}  
	
	/**
	 * 获取附加信息(如果开发者自己需要，可以自己在 App 端进行解析)。
	 *
	 * @returnString
	 */
	public String getExtra() {
		return extra;
	}
	
	/**
	 * 设置附加信息(如果开发者自己需要，可以自己在 App 端进行解析)。
	 *
	 * @return
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}  
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, InfoNtfMessage.class);
	}
}