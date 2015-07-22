package io.rong.models;

import io.rong.util.GsonUtil;

//自定义消息
public class CustomTxtMessage extends Message {

	private String content;

	public CustomTxtMessage(String content) {
		this.type = "KM:TxtMsg";
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
		return GsonUtil.toJson(this, CustomTxtMessage.class);
	}
}
