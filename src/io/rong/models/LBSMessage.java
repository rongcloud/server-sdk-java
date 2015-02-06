package io.rong.models;

import io.rong.util.GsonUtil;

public class LBSMessage extends Message {

	private String content;
	private double latitude;
	private double longitude;
	private String poi;
	private String extra;

	public LBSMessage(String content, double latitude, double longitude,
			String poi) {
		this.type = "RC:LBSMsg";
		this.content = content;
		this.latitude = latitude;
		this.longitude = longitude;
		this.poi = poi;

	}

	public LBSMessage(String content, double latitude, double longitude,
			String poi, String extra) {
		this(content, latitude, longitude, poi);
		this.extra = extra;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getPoi() {
		return poi;
	}

	public void setPoi(String poi) {
		this.poi = poi;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, LBSMessage.class);
	}
}
