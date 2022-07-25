package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * GIF 图片消息。
 *
 */
public class GifMessage extends BaseMessage {

	/**
	* 下载 GIF 图片后存储在本地的图片地址。
	* */
	private String localPath = "";

	/**
	 * GIF 图片的服务器地址。
	 */
	private String remoteUrl = "";

	/**
	 * GIF 图片文件大小，单位为字节 Byte。
	 */
	private Integer gifDataSize = 0;

	/**
	 * GIF 图片高度。
	 */
	private Integer height = 0;

	/**
	 * GIF 图片宽度。
	 */
	private Integer width = 0;
	private UserInfo user = null;
	private transient static final String TYPE = "RC:GIFMsg";

	public GifMessage(String localPath, String remoteUrl, Integer gifDataSize, Integer height, Integer width) {
		this.localPath = localPath;
		this.remoteUrl = remoteUrl;
		this.gifDataSize = gifDataSize;
		this.height = height;
		this.width = width;
	}

	public GifMessage(String localPath, String remoteUrl, Integer gifDataSize, Integer height, Integer width, UserInfo user) {
		this.localPath = localPath;
		this.remoteUrl = remoteUrl;
		this.gifDataSize = gifDataSize;
		this.height = height;
		this.width = width;
		this.user = user;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getRemoteUrl() {
		return remoteUrl;
	}

	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	public Integer getGifDataSize() {
		return gifDataSize;
	}

	public void setGifDataSize(Integer gifDataSize) {
		this.gifDataSize = gifDataSize;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
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
	public String getType() {
		return TYPE;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, GifMessage.class);
	}
}