package io.rong.models.push;

import io.rong.util.GsonUtil;

/**
 * 广播跟参数公共部分
 * 
 * @author jiangxinjun
 * @date 2019-02-27
 */
public class BroadcastPushPublicPart {

    /**
     * 目标操作系统，iOS、Android 最少传递一个。如果需要给两个系统推送消息时，则需要全部填写。（必传）
     */
    private String[] platform;

    /**
     * 推送条件，包括： tag、userid、is_to_all。（必传）
     */
    private Audience audience;

    /**
     * 按操作系统类型推送消息内容
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
