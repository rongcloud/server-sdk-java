package io.rong.models;


public class PushNotification {
	private String alert;
	private PlatformNotification ios;
	private PlatformNotification android;

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public PlatformNotification getIos() {
		return ios;
	}

	public void setIos(PlatformNotification ios) {
		this.ios = ios;
	}

	public PlatformNotification getAndroid() {
		return android;
	}

	public void setAndroid(PlatformNotification android) {
		this.android = android;
	}

}
