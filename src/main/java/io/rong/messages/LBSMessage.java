package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * Location message.
 *
 */
public class LBSMessage extends BaseMessage {
	private String content = "";
	private String extra = "";
	private double latitude = 0;
	private double longitude = 0;
	private String poi = "";
	private UserInfo user = null;
	private transient static final String TYPE = "RC:LBSMsg";
	
	public LBSMessage(String content, String extra, double latitude, double longitude, String poi) {
		this.content = content;
		this.extra = extra;
		this.latitude = latitude;
		this.longitude = longitude;
		this.poi = poi;
	}

	public LBSMessage(String content, String extra, double latitude, double longitude, String poi, UserInfo user) {
		this.content = content;
		this.extra = extra;
		this.latitude = latitude;
		this.longitude = longitude;
		this.poi = poi;
		this.user = user;
	}
	@Override
	public String getType() {
		return TYPE;
	}
	
	/**
	 * Gets the thumbnail of the location image in JPG format. After Base64 encoding, all \r\n, \r, and \ should be replaced with empty strings.
	 *
	 * @return String
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @param content the thumbnail of the location image in JPG format. After Base64 encoding, all \r\n, \r, and \ should be replaced with empty strings.
	 */
	public void setContent(String content) {
		this.content = content;
	}  
	
	/**
	 * Get the additional information (if needed, developers can parse it on the App side).
	 *
	 * @return String
	 */
	public String getExtra() {
		return extra;
	}
	
	/**
	 * @param extra Set the additional information (if needed, developers can parse it on the App side).
	 *
	 *
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}  
	
	/**
	 * Get the latitude.
	 *
	 * @return double
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * @param latitude Set the latitude.
	 *
	 *
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}  
	
	/**
	 * Get the longitude.
	 *
	 * @return double
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * @param longitude Sets the longitude.
	 *
	 *
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}  
	
	/**
	 * Retrieves the location information.
	 *
	 * @return String
	 */
	public String getPoi() {
		return poi;
	}
	
	/**
	 * @param poi  Sets the location information.
	 *
	 *
	 */
	public void setPoi(String poi) {
		this.poi = poi;
	}

	/**
	 * Retrieves the user information carried in the message (the IMKit SDK chat UI prioritizes displaying the user information carried in the message).
	 *
	 * @return UserInfo
	 */
	public UserInfo getUser() {
		return user;
	}

	/**
	 * @param user The user information carried in the message (the IMKit SDK chat UI prioritizes displaying the user information carried in the message).
	 */
	public void setUser(UserInfo user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, LBSMessage.class);
	}
}