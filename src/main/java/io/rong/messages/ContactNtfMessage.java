package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * 添加联系人消息。
 *
 */
public class ContactNtfMessage extends BaseMessage {
	private String operation = "";
	private String extra = "";
	private String sourceUserId = "";
	private String targetUserId = "";
	private String message = "";
	private transient static final String TYPE = "RC:ContactNtf";
	
	public ContactNtfMessage(String operation, String extra, String sourceUserId, String targetUserId, String message) {
		this.operation = operation;
		this.extra = extra;
		this.sourceUserId = sourceUserId;
		this.targetUserId = targetUserId;
		this.message = message;
	}
	
	public String getType() {
		return TYPE;
	}
	
	/**
	 * 获取操作名。
	 *
	 * @returnString
	 */
	public String getOperation() {
		return operation;
	}
	
	/**
	 * 设置操作名。
	 *
	 * @return
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}  
	
	/**
	 * 获取为附加信息(如果开发者自己需要，可以自己在 App 端进行解析)。
	 *
	 * @returnString
	 */
	public String getExtra() {
		return extra;
	}
	
	/**
	 * 设置为附加信息(如果开发者自己需要，可以自己在 App 端进行解析)。
	 *
	 * @return
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}  
	
	/**
	 * 获取请求者或者响应者的 UserId。
	 *
	 * @returnString
	 */
	public String getSourceUserId() {
		return sourceUserId;
	}
	
	/**
	 * 设置请求者或者响应者的 UserId。
	 *
	 * @return
	 */
	public void setSourceUserId(String sourceUserId) {
		this.sourceUserId = sourceUserId;
	}  
	
	/**
	 * 获取被请求者或者被响应者的 UserId。
	 *
	 * @returnString
	 */
	public String getTargetUserId() {
		return targetUserId;
	}
	
	/**
	 * 设置被请求者或者被响应者的 UserId。
	 *
	 * @return
	 */
	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}  
	
	/**
	 * 获取请求或者响应消息。
	 *
	 * @returnString
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 设置请求或者响应消息。
	 *
	 * @return
	 */
	public void setMessage(String message) {
		this.message = message;
	}  
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, ContactNtfMessage.class);
	}
}