package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * 图文消息。
 *
 */
public class ImgTextMessage extends BaseMessage {
	private String content = "";
	private String extra = "";
	private String title = "";
	private String imageUri = "";
	private String url = "";
	private UserInfo user = null;
	private transient static final String TYPE = "RC:ImgTextMsg";

	public ImgTextMessage(String content, String extra, String title, String imageUri, String url) {
		this.content = content;
		this.extra = extra;
		this.title = title;
		this.imageUri = imageUri;
		this.url = url;
	}

	public ImgTextMessage(String content, String extra, String title, String imageUri, String url, UserInfo user) {
		this.content = content;
		this.extra = extra;
		this.title = title;
		this.imageUri = imageUri;
		this.url = url;
		this.user = user;
	}
	@Override
	public String getType() {
		return TYPE;
	}
	
	/**
	 * 获取消息文本内容。
	 *
	 * @return String
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @param content 设置消息文本内容。
	 *
	 *
	 */
	public void setContent(String content) {
		this.content = content;
	}  
	
	/**
	 * 获取附加信息(如果开发者自己需要，可以自己在 App 端进行解析)。
	 *
	 * @return String
	 */
	public String getExtra() {
		return extra;
	}
	
	/**
	 * @param extra 设置附加信息(如果开发者自己需要，可以自己在 App 端进行解析)。
	 *
	 *
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}  
	
	/**
	 * 获取消息标题。
	 *
	 * @return String
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param title 设置消息标题。
	 *
	 *
	 */
	public void setTitle(String title) {
		this.title = title;
	}  
	
	/**
	 * 获取图片地址。
	 *
	 * @return String
	 */
	public String getImageUri() {
		return imageUri;
	}
	
	/**
	 * @param imageUri 设置图片地址。
	 *
	 *
	 */
	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}  
	
	/**
	 * 获取 url 跳转地址。
	 *
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * @param url 设置 url 跳转地址。
	 *
	 *
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取消息中携带的用户信息(IMKit SDK 会话界面中优先显示消息中携带的用户信息)。
	 *
	 * @return UserInfo
	 */
	public UserInfo getUser() {
		return user;
	}

	/**
	 * @param user 消息中携带的用户信息(IMKit SDK 会话界面中优先显示消息中携带的用户信息)。
	 */
	public void setUser(UserInfo user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ImgTextMessage.class);
	}
}