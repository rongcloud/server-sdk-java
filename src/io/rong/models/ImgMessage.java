package io.rong.models;

//图片消息
public class ImgMessage extends Message {

	private String content;
	private String imageKey;
	private String extra;

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public ImgMessage(String content, String imageUri) {
		this.type = "RC:ImgMsg";
		this.content = content;
		this.imageKey = imageUri;
	}

	public ImgMessage(String content, String imageUri, String extra) {
		this(content, imageUri);
		this.extra = extra;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageKey() {
		return imageKey;
	}

	public void setImageKey(String imageKey) {
		this.imageKey = imageKey;
	}

	@Override
	public String toString() {
		return String.format(
				"{\"content\":\"%s\",\"imageUri\":\"%s\",\"extra\":\"%s\"}",
				content, imageKey, extra);
	}
}
