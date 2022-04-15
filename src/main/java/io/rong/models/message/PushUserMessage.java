package io.rong.models.message;

import io.rong.models.push.PlatformNotification;
import io.rong.util.GsonUtil;

import java.util.List;

public class PushUserMessage {
    List<String> userIds;

    Notification notification;

    public List<String> getUserIds() {
        return userIds;
    }

    public PushUserMessage setUserIds(List<String> userIds) {
        this.userIds = userIds;
        return this;
    }

    public Notification getNotification() {
        return notification;
    }

    public PushUserMessage setNotification(Notification notification) {
        this.notification = notification;
        return this;
    }

    public static class Notification {
        /**
         * 通知栏显示标题，最长不超过 50 个字符。
         */
        private String title;

        /**
         * 推送消息内容。
         */
        private String pushContent;

        /**
         * 设置 iOS 平台下的推送及附加信息。
         */
        private PlatformNotification ios;

        /**
         * 设置 Android 平台下的推送及附加信息。
         */
        private PlatformNotification android;

        public String getTitle() {
            return title;
        }

        public Notification setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getPushContent() {
            return pushContent;
        }

        public Notification setPushContent(String pushContent) {
            this.pushContent = pushContent;
            return this;
        }

        public PlatformNotification getIos() {
            return ios;
        }

        public Notification setIos(PlatformNotification ios) {
            this.ios = ios;
            return this;
        }

        public PlatformNotification getAndroid() {
            return android;
        }

        public Notification setAndroid(PlatformNotification android) {
            this.android = android;
            return this;
        }

        @Override
        public String toString() {
            return GsonUtil.toJson(this, Notification.class);
        }
    }
}
