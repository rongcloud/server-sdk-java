package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * 资料通知消息。此类型消息没有 Push 通知。
 *
 */
public class ProfileNtfMessage extends BaseMessage {
	private String operation = "";
	private String data = "";
	private String extra = "";
	private transient static final String TYPE = "RC:ProfileNtf";
	
	public ProfileNtfMessage(String operation, String data, String extra) {
		this.operation = operation;
		this.data = data;
		this.extra = extra;
	}
	@Override
	public String getType() {
		return TYPE;
	}
	
	/**
	 * 获取为资料通知操作，可以自行定义。
	 *
	 * @return String
	 */
	public String getOperation() {
		return operation;
	}
	
	/**
	 * @param operation 设置为资料通知操作，可以自行定义。
	 *
	 *
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}  
	
	/**
	 * 获取操作的数据。
	 *
	 * @return String
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * @param data 设置操作的数据。
	 *
	 *
	 */
	public void setData(String data) {
		this.data = data;
	}  
	
	/**
	 * 获取附加内容(如果开发者自己需要，可以自己在 App 端进行解析)。
	 *
	 * @return String
	 */
	public String getExtra() {
		return extra;
	}
	
	/**
	 * @param extra 设置附加内容(如果开发者自己需要，可以自己在 App 端进行解析)。
	 *
	 *
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}  
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, ProfileNtfMessage.class);
	}
}