package io.rong.models.message;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PushExt {


    private static final String P_MI = "MI";
    private static final String P_HW = "HW";
    private static final String P_HONOR = "HONOR";
    private static final String P_APNS = "APNs";
    private static final String P_VIVO = "VIVO";
    private static final String P_OPPO = "OPPO";
    private static final String P_FCM = "FCM";
    private static final String P_OHOS = "OHOS";

    private static final String CHANNEL_ID = "channelId";
    private static final String IMPORTANCE = "importance";
    private static final String IMAGE = "image";
    private static final String CATEGORY = "category";
    private static final String COLLAPSE_KEY = "collapse_key";
    private static final String IMAGE_URL = "imageUrl";
    private static final String CLASSIFICATION = "classification";
    private static final String THREAD_ID = "thread-id";
    private static final String APNS_COLLAPSE_ID = "apns-collapse-id";
    private static final String RICH_MEDIA_URI = "richMediaUri";
    private static final String INTERRUPTION_LEVEL = "interruption-level";
    private static final String LARGE_ICON_URI = "large_icon_uri";


    private String title;

    private String templateId;

    private int forceShowPushContent;

    private List<Platform> pushConfigs;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public void setForceShowPushContent(int forceShowPushContent) {
        this.forceShowPushContent = forceShowPushContent;
    }

    public int getForceShowPushContent() {
        return this.forceShowPushContent;
    }

    public void setPushConfigs(List<Platform> pushConfigs) {
        this.pushConfigs = pushConfigs;
    }

    public List<Platform> getPushConfigs() {
        return this.pushConfigs;
    }

    /**
     * Retrieves the PushExt JSON parameters.
     *
     * @param title                The title displayed in the notification bar, with a maximum length of 50 characters. By default, the notification title shows the user's name for one-to-one chats and the group name for group chats.
     * @param forceShowPushContent Whether to force the display of notification details. 0 means not forced, 1 means forced. Default is 0. When users set notifications to hide details, this property can be used to force the display of notification details.
     * @param platforms            Configuration parameters for different vendors.
     * @return
     */
    public static PushExt build(String title, Integer forceShowPushContent, Platform... platforms) {
        PushExt pe = new PushExt();
        pe.setTitle(title);
        if (forceShowPushContent != null) {
            pe.setForceShowPushContent(forceShowPushContent);
        }
        List<Platform> pushConfigs = new ArrayList<Platform>();
        for (Platform p : platforms) {
            pushConfigs.add(p);
        }
        pe.setPushConfigs(pushConfigs);
        return pe;
    }

    /**
     * HW.channelId
     * HW.importance
     * HW.image
     * HW.category
     */
    public static class HW implements Platform {
        @SerializedName(P_HW)
        private HashMap<String, String> params = new HashMap<String, String>();

        public HW(String channelId) {
            addParamIfNotBlank(CHANNEL_ID, channelId);
        }

        public HW(String channelId, String importance) {
            addParamIfNotBlank(CHANNEL_ID, channelId);
            addParamIfNotBlank(IMPORTANCE, importance);
        }

        public HW(String channelId, String importance, String image, String category) {
            addParamIfNotBlank(CHANNEL_ID, channelId);
            addParamIfNotBlank(IMPORTANCE, importance);
            addParamIfNotBlank(IMAGE, image);
            addParamIfNotBlank(CATEGORY, category);
        }

        @Override
        public void addParamIfNotBlank(String name, String param) {
            if (StringUtils.isNotBlank(param)) {
                params.put(name, param);
            }
        }


    }

    /**
     * HONOR.importance
     * HONOR.image
     */
    public static class HONOR implements Platform {
        @SerializedName(P_HONOR)
        private HashMap<String, String> params = new HashMap<String, String>();

        public HONOR(String importance) {
            addParamIfNotBlank(IMPORTANCE, importance);
        }

        public HONOR(String importance, String image) {
            addParamIfNotBlank(IMPORTANCE, importance);
            addParamIfNotBlank(IMAGE, image);
        }

        @Override
        public void addParamIfNotBlank(String name, String param) {
            if (StringUtils.isNotBlank(param)) {
                params.put(name, param);
            }
        }


    }

    /**
     * MI.channelId
     * MI.large_icon_uri
     */
    public static class MI implements Platform {
        @SerializedName(P_MI)
        private HashMap<String, String> params = new HashMap<String, String>();

        public MI(String channelId, String largeIconUri) {
            addParamIfNotBlank(CHANNEL_ID, channelId);
            addParamIfNotBlank(LARGE_ICON_URI, largeIconUri);
        }

        @Override
        public void addParamIfNotBlank(String name, String param) {
            if (StringUtils.isNotBlank(param)) {
                params.put(name, param);
            }
        }

    }

    /**
     * APNs.thread-id
     * APNs.apns-collapse-id
     * APNs.richMediaUri
     * APNs.interruption-level
     */

    public static class APNs implements Platform {
        @SerializedName(P_APNS)
        private HashMap<String, String> params = new HashMap<String, String>();

        public APNs(String threadId, String apnsCollapseId) {
            addParamIfNotBlank(THREAD_ID, threadId);
            addParamIfNotBlank(APNS_COLLAPSE_ID, apnsCollapseId);
        }

        public APNs(String threadId, String apnsCollapseId, String richMediaUri, String interruptionLevel) {
            addParamIfNotBlank(THREAD_ID, threadId);
            addParamIfNotBlank(APNS_COLLAPSE_ID, apnsCollapseId);
            addParamIfNotBlank(RICH_MEDIA_URI, richMediaUri);
            addParamIfNotBlank(INTERRUPTION_LEVEL, interruptionLevel);
        }

        @Override
        public void addParamIfNotBlank(String name, String param) {
            if (StringUtils.isNotBlank(param)) {
                params.put(name, param);
            }
        }

    }

    /**
     * VIVO.classification
     * VIVO.category
     */
    public static class VIVO implements Platform {
        @SerializedName(P_VIVO)
        private HashMap<String, String> params = new HashMap<String, String>();

        public VIVO(String classification) {
            addParamIfNotBlank(CLASSIFICATION, classification);
        }

        public VIVO(String classification, String category) {
            addParamIfNotBlank(CLASSIFICATION, classification);
            addParamIfNotBlank(CATEGORY, category);
        }

        @Override
        public void addParamIfNotBlank(String name, String param) {
            if (StringUtils.isNotBlank(param)) {
                params.put(name, param);
            }
        }

    }

    /**
     * OPPO.channelId
     */
    public static class OPPO implements Platform {
        @SerializedName(P_OPPO)
        private HashMap<String, String> params = new HashMap<String, String>();

        public OPPO(String channelId) {
            addParamIfNotBlank(CHANNEL_ID, channelId);
        }

        @Override
        public void addParamIfNotBlank(String name, String param) {
            if (StringUtils.isNotBlank(param)) {
                params.put(name, param);
            }
        }

    }

    /**
     * FCM.channelId
     * FCM.collapse_key
     * FCM.imageUrl
     */
    public static class FCM implements Platform {
        @SerializedName(P_FCM)
        protected HashMap<String, String> params = new HashMap<String, String>();

        public FCM(String channelId) {
            addParamIfNotBlank(CHANNEL_ID, channelId);
        }

        public FCM(String channelId, String collapseKey, String imageUrl) {
            addParamIfNotBlank(CHANNEL_ID, channelId);
            addParamIfNotBlank(COLLAPSE_KEY, collapseKey);
            addParamIfNotBlank(IMAGE_URL, imageUrl);
        }

        @Override
        public void addParamIfNotBlank(String name, String param) {
            if (StringUtils.isNotBlank(param)) {
                params.put(name, param);
            }
        }

    }


    /**
     * OHOS.category
     * OHOS.image
     */
    public static class OHOS implements Platform {
        @SerializedName(P_OHOS)
        protected HashMap<String, String> params = new HashMap<String, String>();

        public OHOS(String category, String image) {
            addParamIfNotBlank(CATEGORY, category);
            addParamIfNotBlank(IMAGE, image);
        }

        @Override
        public void addParamIfNotBlank(String name, String param) {
            if (StringUtils.isNotBlank(param)) {
                params.put(name, param);
            }
        }
    }


}
