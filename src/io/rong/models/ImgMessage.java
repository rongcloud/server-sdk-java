package io.rong.models;

//图片消息
public class ImgMessage extends Message{

	private String content;
	private String imageKey;

	public ImgMessage(String content,String imageKey) {
		this.type = "RC:ImgMsg";
		this.content = content;
		this.imageKey = imageKey;
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
		return String.format("{\"content\":\"%s\",\"imageKey\":\"%s\"}", content,imageKey);
	}
}
