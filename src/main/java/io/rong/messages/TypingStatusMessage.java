package io.rong.messages;

import io.rong.util.GsonUtil;

public class TypingStatusMessage extends BaseMessage {

	private String content = "{\"typingContentType\":\"RC:TxtMsg\"}";
	private transient static final String TYPE = "RC:TypSts";

	public TypingStatusMessage() {

	}

	@Override
	public String getType() {
		return TYPE;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, TypingStatusMessage.class);
	}

}
