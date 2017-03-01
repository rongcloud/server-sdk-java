package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * 图片消息。
 *
 */
public class ImgMessage extends BaseMessage {
	private String content = "";
	private String extra = "";
	private String imageUri = "";
	private transient static final String TYPE = "RC:ImgMsg";
	
	public ImgMessage(String content, String extra, String imageUri) {
		this.content = content;
		this.extra = extra;
		this.imageUri = imageUri;
	}
	
	public String getType() {
		return TYPE;
	}
	
	/**
	 * 获取表示图片缩略图，格式为 JPG，大小不超过 30k，注意在 Base64 进行 Encode 后需要将所有 \r\n 和 \r 和 \n 替换成空。
	 *
	 * @returnString
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * 设置表示图片缩略图，格式为 JPG，大小不超过 30k，注意在 Base64 进行 Encode 后需要将所有 \r\n 和 \r 和 \n 替换成空。
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
	 * 获取图片 Url。
	 *
	 * @returnString
	 */
	public String getImageUri() {
		return imageUri;
	}
	
	/**
	 * 设置图片 Url。
	 *
	 * @return
	 */
	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}  
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, ImgMessage.class);
	}
}