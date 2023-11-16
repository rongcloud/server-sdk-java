package io.rong.models.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

public class PushExt {
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
     * 获取PushExt json参数
     *
     * @param title                通知栏显示标题，最长不超过 50 个字符，默认情况下通知标题单聊会话显示用户名称，群聊会话显示群名称。
     * @param forceShowPushContent 是否强制显示通知详细，0 为不强制、1 为强制，默认为 0，当用户设置通知不显示详细时，可通过此属性强制显示通知详细。
     * @param platforms            不同厂商配置参数
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

    public static class HW implements Platform {
        private HashMap<String, String> HW = new HashMap<String, String>();

        public HW(String channelId) {
            HW.put("channelId", channelId);
        }

        public HW(String channelId, String importance) {
            HW.put("channelId", channelId);
            HW.put("importance", importance);
        }

        public HashMap<String, String> getHW() {
            return HW;
        }

        public void setHW(HashMap<String, String> hW) {
            this.HW = hW;
        }

    }

    public static class HONOR implements Platform {
        private HashMap<String, String> HONOR = new HashMap<String, String>();

        public HONOR(String importance) {
            HONOR.put("importance", importance);
        }

        public HONOR(String importance, String image) {
            HONOR.put("importance", importance);
            HONOR.put("image", image);
        }

        public HashMap<String, String> getHONOR() {
            return HONOR;
        }

        public void setHONOR(HashMap<String, String> honor) {
            this.HONOR = honor;
        }

    }


    public static class APNs implements Platform {
        private HashMap<String, String> APNs = new HashMap<String, String>();

        public APNs(String thread_id, String apns_collapse_id) {
            APNs.put("thread-id", thread_id);
            APNs.put("apns-collapse-id", apns_collapse_id);
        }

        public HashMap<String, String> getAPNs() {
            return APNs;
        }

        public void setAPNs(HashMap<String, String> aPNs) {
            this.APNs = aPNs;
        }
    }

    public static class VIVO implements Platform {
        private HashMap<String, String> VIVO = new HashMap<String, String>();

        public VIVO(String classification) {
            VIVO.put("classification", classification);
        }

        public HashMap<String, String> getVIVO() {
            return VIVO;
        }

        public void setVIVO(HashMap<String, String> VIVO) {
            this.VIVO = VIVO;
        }

    }

    public static class OPPO implements Platform {
        private HashMap<String, String> OPPO = new HashMap<String, String>();

        public OPPO(String channelId) {
            OPPO.put("channelId", channelId);
        }

        public HashMap<String, String> getOPPO() {
            return OPPO;
        }

        public void setOPPO(HashMap<String, String> OPPO) {
            this.OPPO = OPPO;
        }

    }

    public static class FCM implements Platform {
        private HashMap<String, String> FCM = new HashMap<String, String>();

        public FCM(String channelId) {
            FCM.put("channelId", channelId);
        }

        public HashMap<String, String> getFCM() {
            return FCM;
        }

        public void setFCM(HashMap<String, String> FCM) {
            this.FCM = FCM;
        }

    }


}
