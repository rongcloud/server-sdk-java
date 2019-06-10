package io.rong.models.push;

import io.rong.util.GsonUtil;

/**
 * 按操作系统类型推送消息内容，如 platform 中设置了给 iOS 和 Android 系统推送消息，而在 notification 中只设置了 iOS
 * 的推送内容，则 Android 的推送内容为最初 alert 设置的内容。（非必传）
 */
public class Notification {

    /**
     * 默认推送消息内容，如填写了 iOS 或 Android 下的 alert 时，则推送内容以对应平台系统的 alert 为准。（必传）
     */
    private String alert;

    /**
     * 设置 iOS 平台下的推送及附加信息。
     */
    private PlatformNotification ios;

    /**
     * 设置 Android 平台下的推送及附加信息。
     */
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

    @Override
    public String toString() {
        return GsonUtil.toJson(this, Notification.class);
    }
}