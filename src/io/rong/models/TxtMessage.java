package io.rong.models;

//文本消息
public class TxtMessage extends Message {

	private String content;

	public TxtMessage(String content) {
		this.type = "RC:TxtMsg";
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return String.format("{\"content\":\"%s\"}", content);
	}
}
