package io.rong.models;


//消息基类，如实现自定义消息，可继承此类
public abstract class Message {

	protected transient String type;

	public String getType() {
		return type;
	}

	public abstract String toString();
}
