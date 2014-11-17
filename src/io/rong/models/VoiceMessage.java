package io.rong.models;

//语音消息
public class VoiceMessage extends Message {
	
	private String content;
	private Long duration;

	public VoiceMessage(String content,Long duration) {
		this.type = "RC:VcMsg";
		this.content = content;
		this.duration = duration;
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
		return String.format("{\"content\":\"%s\",\"duration\":\"%s\"}", content,duration);
	}
}
