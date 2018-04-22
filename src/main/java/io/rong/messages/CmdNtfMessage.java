package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * 通用命令通知消息。此类型消息没有 Push 通知。
 *
 */
public class CmdNtfMessage extends BaseMessage {
	private String name = "";
	private String data = "";
	private transient static final String TYPE = "RC:CmdNtf";
	
	public CmdNtfMessage(String name, String data) {
		this.name = name;
		this.data = data;
	}
	@Override
	public String getType() {
		return TYPE;
	}
	
	/**
	 * 获取命令名称，可以自行定义
	 *
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name  设置命令名称，可以自行定义
	 *
	 *
	 */
	public void setName(String name) {
		this.name = name;
	}  
	
	/**
	 * 获取命令的内容
	 *
	 * @return String
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * @param data 设置命令的内容
	 *
	 *
	 */
	public void setData(String data) {
		this.data = data;
	}  
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, CmdNtfMessage.class);
	}
}