package io.rong.models;

import io.rong.util.GsonUtil;
import java.util.Map;

/**
 * 设备中的推送内容。（非必传）
 */
public class PlatformNotification {
	// 默认推送消息内容，如填写了 ios 或 android 下的 alert 时，则推送内容以对应平台系统的 alert 为准。（必传）
	String alert;
	// ios 或 android 不同平台下的附加信息，如果开发者自己需要，可以自己在 App 端进行解析。（非必传）
	Map<String,String> extras;
	
	public PlatformNotification(String alert, Map<String,String> extras) {
		this.alert = alert;
		this.extras = extras;
	}
	
	/**
	 * 设置alert
	 *
	 */	
	public void setAlert(String alert) {
		this.alert = alert;
	}
	
	/**
	 * 获取alert
	 *
	 * @return String
	 */
	public String getAlert() {
		return alert;
	}
	
	/**
	 * 设置extras
	 *
	 */	
	public void setExtras(Map<String,String> extras) {
		this.extras = extras;
	}
	
	/**
	 * 获取extras
	 *
	 * @return Map<String,String>
	 */
	public Map<String,String> getExtras() {
		return extras;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, PlatformNotification.class);
	}
}
