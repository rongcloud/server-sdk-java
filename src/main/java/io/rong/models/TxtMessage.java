package io.rong.models;

import io.rong.util.GsonUtil;

//文本消息
public class TxtMessage extends Message {

	private String content;
	private String extra;

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	public TxtMessage(String content) {
		this.type = "RC:TxtMsg";
		this.content = content;
	}

	public TxtMessage(String content,String extra) {
		this(content);
		this.extra = extra;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, TxtMessage.class);
	}
}
