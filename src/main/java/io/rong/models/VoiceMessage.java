package io.rong.models;

import io.rong.util.GsonUtil;

//语音消息
public class VoiceMessage extends Message {
	
	private String content;
	private Long duration;
	private String extra;

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public VoiceMessage(String content,Long duration) {
		this.type = "RC:VcMsg";
		this.content = content;
		this.duration = duration;
	}
	
	public VoiceMessage(String content,Long duration,String extra) {
		this(content,duration);
		this.extra = extra;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {		
		return GsonUtil.toJson(this, VoiceMessage.class);
	}
}
