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
     * 通知栏显示标题，最长不超过 50 个字符。
     */
    private String title;

    /**
     * 是否强制显示通知详细，0 为不强制、1 为强制，默认为 0，当用户设置通知不显示详细时，可通过此属性强制显示通知详细。
     */
    private Integer forceShowPushContent;


    /**
     * 设置 iOS 平台下的推送及附加信息。
     */
    private PlatformNotification ios;

    /**
     * 设置 Android 平台下的推送及附加信息。
     */
    private PlatformNotification android;
    /**
     * 设置鸿蒙平台下的推送及附加信息。
     */
    private HarmonyOSPlatformNotification harmonyOS;

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

    public HarmonyOSPlatformNotification getHarmonyOS() {
        return harmonyOS;
    }

    public void setHarmonyOS(HarmonyOSPlatformNotification harmonyOS) {
        this.harmonyOS = harmonyOS;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getForceShowPushContent() {
        return forceShowPushContent;
    }

    public void setForceShowPushContent(Integer forceShowPushContent) {
        this.forceShowPushContent = forceShowPushContent;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, Notification.class);
    }
}