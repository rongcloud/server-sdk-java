package io.rong.models.push;

import io.rong.util.GsonUtil;

/**
 * Common parameters for broadcast push.
 */
public class BroadcastPushPublicPart {

    /**
     * Target operating systems. At least one of iOS or Android must be specified. If you need to push messages to both systems, both must be filled. (Required)
     */
    private String[] platform;

    /**
     * Push conditions, including: tag, userid, is_to_all. (Required)
     */
    private Audience audience;

    /**
     * Push message content by operating system type.
     */
    private Notification notification;

    public String[] getPlatform() {
        return platform;
    }

    public void setPlatform(String[] platform) {
        this.platform = platform;
    }

    public Audience getAudience() {
        return audience;
    }

    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, BroadcastPushPublicPart.class);
    }
}