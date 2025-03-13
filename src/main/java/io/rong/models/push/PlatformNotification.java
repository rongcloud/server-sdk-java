package io.rong.models.push;

import com.google.gson.annotations.SerializedName;
import io.rong.util.GsonUtil;

import java.util.Map;

/**
 * Push notification content for the device. (Optional)
 */
public class PlatformNotification {

    /**
     * Default push notification message content. If the alert under iOS or Android is filled, the push content will follow the alert of the corresponding platform. (Required)
     */
    private String alert;

    /**
     * The title displayed in the notification bar, specific to the iOS platform, supported on iOS 8.2 and above. The parameter is set under the ios node. For details, refer to the "Set iOS Push Title Request Example". (Optional)
     */
    private String title;

    /**
     * For the iOS platform, it enables silent push notifications when the SDK is in the background suspended state, a push method introduced after iOS7.
     * Allows the app to run a piece of code in the background immediately after receiving the notification. 1 means enabled, 0 means disabled, default is 0. (Optional)
     */
    private Integer contentAvailable;

    /**
     * Additional information for different platforms (iOS or Android). Developers can parse it on the App side if needed. (Optional)
     */
    private Map<String, String> extras;

    /**
     * App badge, specific to the iOS platform; if not filled, the badge number remains unchanged; when 0 or a negative number, the badge number on the App is cleared; otherwise, the specified number will be displayed, with a maximum of 9999. The parameter is set under the ios node. For details, refer to the "Set iOS Badge Number HTTP Request Example". (Optional)
     */
    private Integer badge;

    /**
     * The type of iOS rich push notification is defined by the developer and parsed on the App side, used together with richMediaUri. (Optional)
     */
    private String category;

    /**
     * The URL of the iOS rich push notification content, used together with category. (Optional)
     */
    private String richMediaUri;

    /**
     * iOS platform notification grouping ID. Notifications with the same thread-id are grouped together. If a single group exceeds 5 notifications, they will be collapsed.
     */
    @SerializedName("thread-id")
    private String threadId;

    /**
     * iOS platform, supported since iOS 10. When set, messages with the same ID received by the device will be merged into one.
     */
    @SerializedName("apns-collapse-id")
    private String apnsCollapseId;

    /**
     * Applicable to iOS 15 and later systems. Values include passive, active (default), time-sensitive, or critical. For details, refer to the APNs interruption-level field. In iOS 15 and later, system features like "Scheduled Summary" and "Focus Mode" may prevent important notifications (e.g., balance changes) from being promptly noticed by users. Consider setting this field to address this.
     */
    @SerializedName("interruption-level")
    private String interruptionLevel;

    private Platform hw;
    private Platform honor;
    private Platform mi;
    private Platform oppo;
    private Platform vivo;
    private Platform fcm;

    //=====common setters=====//
    public void setHw(Platform hw) {
        this.hw = hw;
    }

    public void setHonor(Platform honor) {
        this.honor = honor;
    }

    public void setMi(Platform mi) {
        this.mi = mi;
    }

    public void setOppo(Platform oppo) {
        this.oppo = oppo;
    }

    public void setVivo(Platform vivo) {
        this.vivo = vivo;
    }

    public void setFcm(Platform fcm) {
        this.fcm = fcm;
    }

    //=====common setters=====//

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getContentAvailable() {
        return contentAvailable;
    }

    public void setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }

    public Integer getBadge() {
        return badge;
    }

    public void setBadge(Integer badge) {
        this.badge = badge;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRichMediaUri() {
        return richMediaUri;
    }

    public void setRichMediaUri(String richMediaUri) {
        this.richMediaUri = richMediaUri;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getApnsCollapseId() {
        return apnsCollapseId;
    }

    public void setApnsCollapseId(String apnsCollapseId) {
        this.apnsCollapseId = apnsCollapseId;
    }

    public Platform getHw() {
        return hw;
    }

    public void setHw(String channelId) {
        this.hw = new Platform(channelId);
    }

    public Platform getHonor() {
        return honor;
    }

    public void setHonor(String importance, String image) {
        this.honor = new Platform();
        this.honor.setImportance(importance);
        this.honor.setImage(image);
    }

    public Platform getMi() {
        return mi;
    }

    public void setMi(String channelId) {
        this.mi = new Platform(channelId);
    }

    public void setFcm(String channelId) {
        this.fcm = new Platform(channelId);
    }

    public Platform getOppo() {
        return oppo;
    }

    public void setOppo(String channelId) {
        this.oppo = new Platform(channelId);
    }

    public Platform getVivo() {
        return vivo;
    }

    public void setVivo(String classification) {
        this.vivo = new Platform(null, classification);
    }

    public static class Platform {

        //hw mi oppo fcm
        private String channelId;

        //vivo
        private String classification;
        //hw honor
        private String importance;
        //hw honor
        private String image;
        //mi
        @SerializedName("large_icon_uri")
        private String largeIconUri;
        //fcm
        private String imageUrl;

        @SerializedName("collapse_key")
        private String collapseKey;
        // hw vivo
        private String category;

        /**
         * version >= 3.6.1
         * OPPO push notification level definitions
         * 1 - Notification bar
         * 2 - Notification bar + Lock screen
         * 16 - Notification bar + Lock screen + Banner + Vibration + Ringtone
         * <p>


         /**
         * When using the notify_level parameter, the category parameter is required.
         */
        @SerializedName("notify_level")
        private Integer notifyLevel;

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getClassification() {
            return classification;
        }

        public void setClassification(String classification) {
            this.classification = classification;
        }

        public String getImportance() {
            return importance;
        }

        public void setImportance(String importance) {
            this.importance = importance;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLargeIconUri() {
            return largeIconUri;
        }

        public void setLargeIconUri(String largeIconUri) {
            this.largeIconUri = largeIconUri;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getCollapseKey() {
            return collapseKey;
        }

        public void setCollapseKey(String collapseKey) {
            this.collapseKey = collapseKey;
        }

        public Platform() {
        }

        public Platform(String channelId) {
            this.channelId = channelId;
        }

        public Platform(String channelId, String classification) {
            this.channelId = channelId;
            this.classification = classification;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Integer getNotifyLevel() {
            return notifyLevel;
        }

        public void setNotifyLevel(Integer notifyLevel) {
            this.notifyLevel = notifyLevel;
        }
    }

    public String getInterruptionLevel() {
        return interruptionLevel;
    }

    public void setInterruptionLevel(String interruptionLevel) {
        this.interruptionLevel = interruptionLevel;
    }

    public Platform getFcm() {
        return fcm;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PlatformNotification.class);
    }
}