package io.rong.messages;

import io.rong.models.message.MentionedInfo;

/**
 *
 * 消息基类，如实现自定义消息，可继承此类
 *
 **/
public abstract class BaseMessage {

	protected MentionedInfo mentionedInfo;

	public abstract String getType();

	@Override
	public abstract String toString();

	public MentionedInfo getMentionedInfo() {
		return mentionedInfo;
	}

	public void setMentionedInfo(MentionedInfo mentionedInfo) {
		this.mentionedInfo = mentionedInfo;
	}
}
