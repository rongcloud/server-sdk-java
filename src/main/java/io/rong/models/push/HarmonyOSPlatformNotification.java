package io.rong.models.push;

import io.rong.util.GsonUtil;

import java.util.Map;

/**
 * 设备中的推送内容。（非必传）
 */
public class HarmonyOSPlatformNotification {

    /**
     * 非必传，鸿蒙平台下推送通知内容，传入后默认的推送通知内容失效。
     */
    private String alert;

    /**
     * 非必传，附加信息，如果开发者自己需要，可以自己在 App 端进行解析。
     */
    private Map<String, String> extras;


    /**
     * 鸿蒙平台推送参数设置
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
         * 通知右侧大图标URL，URL使用的协议必须是HTTPS协议，取值样例：https://example.com/image.png。
         * 图片格式支持png、jpg、jpeg、heif、gif、bmp，图片长*宽 < 25000像素，图片不满足要求的情况下，终端不能显示通知消息。
         */
        private String image;

        /**
         * 推送通道的消息自分类标识
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