package io.rong.models.push;

import io.rong.util.GsonUtil;

import java.util.Map;

/**
 * Push notification content for the device. (Optional)
 */
public class HarmonyOSPlatformNotification {

    /**
     * Optional. The notification content for the HarmonyOS platform. If provided, the default notification content will be overridden.
     */
    private String alert;

    /**
     * Optional. Additional information that can be parsed by the App as needed.
     */
    private Map<String, String> extras;


    /**
     * Push notification settings for the HarmonyOS platform.
     */
    private HarmonyOSSettings ohos;


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

    public HarmonyOSSettings getOhos() {
        return ohos;
    }

    public void setOhos(HarmonyOSSettings ohos) {
        this.ohos = ohos;
    }


    public static class HarmonyOSSettings {

        /**
         * The URL of the large icon on the right side of the notification. The URL must use the HTTPS protocol. Example: https://example.com/image.png.
         * Supported image formats include png, jpg, jpeg, heif, gif, and bmp. The image must be less than 25,000 pixels in length * width. If the image does not meet these requirements, the notification message will not be displayed on the device.
         */
        private String image;

        /**
         * The self-classification identifier for the push channel message.
         */
        private String category;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, HarmonyOSPlatformNotification.class);
    }
}