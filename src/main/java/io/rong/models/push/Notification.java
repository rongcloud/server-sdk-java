package io.rong.models.push;

import io.rong.util.GsonUtil;

/**
 * Push notification content by operating system type. For example, if the platform is set to push messages to iOS and Android,
 * but only the iOS push content is set in the notification, then the Android push content will be the initial alert content. (Optional)
 */
public class Notification {

    /**
     * Default push notification content. If the alert for iOS or Android is specified, the push content will follow the alert of the corresponding platform. (Required)
     */
    private String alert;

    /**
     * The title displayed in the notification bar, with a maximum length of 50 characters.
     */
    private String title;

    /**
     * Whether to force the display of notification details. 0 for not forced, 1 for forced. Default is 0. When the user sets the notification to not show details,
     * this property can be used to force the display of notification details.
     */
    private Integer forceShowPushContent;


    /**
     * Set the push notification and additional information for the iOS platform.
     */
    private PlatformNotification ios;

    /**
     * Set the push notification and additional information for the Android platform.
     */
    private PlatformNotification android;
    /**
     * Set the push notification and additional information for the HarmonyOS platform.
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