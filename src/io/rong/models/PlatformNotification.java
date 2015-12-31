package io.rong.models;

import java.util.Map;

public class PlatformNotification {
	private String alert;
	private Map<String, String> extras;

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public Map<String, String> getExtras() {
		return extras;
	}

	public void setExtras(Map<String, String> extras) {
		this.extras = extras;
	}

}
