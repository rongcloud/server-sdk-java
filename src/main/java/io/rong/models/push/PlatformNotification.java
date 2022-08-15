package io.rong.models.push;

import java.util.Map;

import com.google.gson.annotations.SerializedName;
import io.rong.models.message.Platform;
import io.rong.util.GsonUtil;

/**
 * 设备中的推送内容。（非必传）
 */
public class PlatformNotification {

    /**
     * 默认推送消息内容，如填写了 iOS 或 Android 下的 alert 时，则推送内容以对应平台系统的 alert 为准。（必传）
     */
    private String alert;

    /**
     * 通知栏显示的推送标题，仅针对 iOS 平台，支持 iOS 8.2 及以上版本，参数在 ios 节点下设置，详细可参考“设置 iOS
     * 推送标题请求示例”。（非必传）
     */
    private String title;

    /**
     * 针对 iOS 平台，对 SDK 处于后台暂停状态时为静默推送，是 iOS7 之后推出的一种推送方式。
     * 允许应用在收到通知后在后台运行一段代码，且能够马上执行，查看详细。1 表示为开启，0 表示为关闭，默认为 0（非必传）
     */
    private Integer contentAvailable;

    /**
     * iOS 或 Android 不同平台下的附加信息，如果开发者自己需要，可以自己在 App 端进行解析。（非必传）
     */
    private Map<String, String> extras;

    /**
     * 应用角标，仅针对 iOS 平台；不填时，表示不改变角标数；为 0 或负数时，表示 App
     * 角标上的数字清零；否则传相应数字表示把角标数改为指定的数字，最大不超过 9999，参数在 ios 节点下设置，详细可参考“设置 iOS 角标数 HTTP
     * 请求示例”。（非必传）
     */
    private Integer badge;

    /**
     * iOS 富文本推送的类型开发者自已定义，自已在 App 端进行解析判断，与 richMediaUri 一起使用。（非必传）
     */
    private String category;

    /**
     * iOS 富文本推送内容的 URL，与 category 一起使用。（非必传）
     */
    private String richMediaUri;

    /**
     * iOS 平台通知栏分组 ID，相同的 thread-id 推送分一组，单组超过 5 条推送会折叠展示
     */
    @SerializedName("thread-id")
    private String thread_id;

    /**
     * iOS 平台，从 iOS10 开始支持，设置后设备收到有相同 ID 的消息，会合并成一条
     */
    @SerializedName("apns-collapse-id")
    private String apns_collapse_id;

    private Platform hw;
    private Platform mi;
    private Platform oppo;
    private Platform vivo;

    //=====common setters=====//
    public void setHw(Platform hw) {
        this.hw = hw;
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

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getApns_collapse_id() {
        return apns_collapse_id;
    }

    public void setApns_collapse_id(String apns_collapse_id) {
        this.apns_collapse_id = apns_collapse_id;
    }

    public Platform getHw() {
        return hw;
    }

    public void setHw(String channelId) {
        this.hw = new Platform(channelId);
    }

    public Platform getMi() {
        return mi;
    }

    public void setMi(String channelId) {
        this.mi = new Platform(channelId);
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
        //hw mi oppo
        private String channelId;

        //vivo
        private String classification;
        //hw
        private String importance;
        //hw
        private String image;
        //mi
        private String large_icon_uri;

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

        public String getLarge_icon_uri() {
            return large_icon_uri;
        }

        public void setLarge_icon_uri(String large_icon_uri) {
            this.large_icon_uri = large_icon_uri;
        }

        public Platform(String channelId) {
            this.channelId = channelId;
        }

        public Platform(String channelId, String classification) {
            this.channelId = channelId;
            this.classification = classification;
        }
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PlatformNotification.class);
    }
}