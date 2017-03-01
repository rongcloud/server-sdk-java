package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * 语音消息。
 *
 */
public class VoiceMessage extends BaseMessage {
	private String content = "";
	private String extra = "";
	private Long duration = 0L;
	private transient static final String TYPE = "RC:VcMsg";
	
	public VoiceMessage(String content, String extra, Long duration) {
		this.content = content;
		this.extra = extra;
		this.duration = duration;
	}
	
	public String getType() {
		return TYPE;
	}
	
	/**
	 * 获取表示语音内容，格式为 AMR，以 Base64 进行 Encode 后需要将所有 \r\n 和 \r 和 \n 替换成空，大小不超过 60k，duration 表示语音长度，最长为 60 秒。
	 *
	 * @returnString
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * 设置表示语音内容，格式为 AMR，以 Base64 进行 Encode 后需要将所有 \r\n 和 \r 和 \n 替换成空，大小不超过 60k，duration 表示语音长度，最长为 60 秒。
	 *
	 * @return
	 */
	public void setContent(String content) {
		this.content = content;
	}  
	
	/**
	 * 获取为附加信息(如果开发者自己需要，可以自己在 App 端进行解析)。
	 *
	 * @returnString
	 */
	public String getExtra() {
		return extra;
	}
	
	/**
	 * 设置为附加信息(如果开发者自己需要，可以自己在 App 端进行解析)。
	 *
	 * @return
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}  
	
	/**
	 * 获取持续时间。
	 *
	 * @returnLong
	 */
	public Long getDuration() {
		return duration;
	}
	
	/**
	 * 设置持续时间。
	 *
	 * @return
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}  
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, VoiceMessage.class);
	}
}