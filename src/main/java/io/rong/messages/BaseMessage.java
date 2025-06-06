package io.rong.messages;

import io.rong.models.message.Audit;
import io.rong.models.message.MentionedInfo;

/**
 *
 * Base class for messages. If you want to implement a custom message, you can inherit from this class.
 *
 **/
public abstract class BaseMessage {

	protected MentionedInfo mentionedInfo;
	/**
	 *
	 * The audit configuration of the message.
	 *
	 **/
	protected Audit audit;

	public abstract String getType();

	@Override
	public abstract String toString();

	public MentionedInfo getMentionedInfo() {
		return mentionedInfo;
	}

	public void setMentionedInfo(MentionedInfo mentionedInfo) {
		this.mentionedInfo = mentionedInfo;
	}

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}
}
