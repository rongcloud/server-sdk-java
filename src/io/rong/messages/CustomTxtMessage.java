package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * 自定义消息
 *
 */
public class CustomTxtMessage extends BaseMessage {
	private String content = "";
	private transient static final String TYPE = "RC:TxtMsg";
	
	public CustomTxtMessage(String content) {
		this.content = content;
	}
	
	public String getType() {
		return TYPE;
	}
	
	/**
	 * 获取自定义消息内容。
	 *
	 * @returnString
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * 设置自定义消息内容。
	 *
	 * @return
	 */
	public void setContent(String content) {
		this.content = content;
	}  
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, CustomTxtMessage.class);
	}
}