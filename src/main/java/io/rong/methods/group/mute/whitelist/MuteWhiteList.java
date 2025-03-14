package io.rong.methods.group.mute.whitelist;

import io.rong.RongCloud;
import io.rong.methods.group.ban.whitelist.User;
/**
 * Group Mute Whitelist Service
 * When a group is muted, if certain users need to be allowed to speak, they can be added to the group mute whitelist. The group mute whitelist only takes effect when the group is set to mute all members.
 * @author rc
 *
 * */
public class MuteWhiteList {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "group/ban/whitelist";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;
    public User user;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public MuteWhiteList(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
        this.user = new User(appKey,appSecret,rongCloud);

    }

    public static String getUTF8() {
        return UTF8;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
