package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * GIF message.
 *
 */
public class GifMessage extends BaseMessage {

	/**
	* The local path where the GIF image is stored after downloading.
	* */
	private String localPath = "";

	/**
	 * The server URL of the GIF image.
	 */
	private String remoteUrl = "";

	/**
	 * The file size of the GIF image in bytes.
	 */
	private Integer gifDataSize = 0;

	/**
	 * The height of the GIF image.
	 */
	private Integer height = 0;

	/**
	 * The width of the GIF image.
	 */
	private Integer width = 0;
	private UserInfo user = null;

	private String extra;
	private static final String TYPE = "RC:GIFMsg";

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
	 * Retrieves the user information carried in the message (The chat UI in the IMKit SDK prioritizes displaying the user information carried in the message).
	 */
	public UserInfo getUser() {
		return user;
	}

	/**
	 * @param user User information carried in the message (The chat UI in IMKit SDK prioritizes displaying the user information carried in the message).
	 */
	public void setUser(UserInfo user) {
		this.user = user;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
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