package io.rong.models;

import io.rong.util.GsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushMessage {
	private List<String> platform;
	private String fromuserid;
	private TagObj audience;
	private MsgObj message;
	private PushNotification notification;

	public List<String> getPlatform() {
		return platform;
	}

	public void setPlatform(List<String> platform) {
		this.platform = platform;
	}

	public String getFromuserid() {
		return fromuserid;
	}

	public void setFromuserid(String fromuserid) {
		this.fromuserid = fromuserid;
	}

	public TagObj getAudience() {
		return audience;
	}

	public void setAudience(TagObj audience) {
		this.audience = audience;
	}

	public MsgObj getMessage() {
		return message;
	}

	public void setMessage(MsgObj message) {
		this.message = message;
	}

	public PushNotification getNotification() {
		return notification;
	}

	public void setNotification(PushNotification notification) {
		this.notification = notification;
	}

	public PushMessage() {
		super();
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, PushMessage.class);
	}
}
