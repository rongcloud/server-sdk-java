package io.rong.models;

import io.rong.util.GsonUtil;

//图片消息
public class ImgMessage extends Message {

	private String content;
	private String imageUri;
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
		this.imageUri = imageUri;
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
		return imageUri;
	}

	public void setImageKey(String imageUri) {
		this.imageUri = imageUri;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ImgMessage.class);
	}
}
