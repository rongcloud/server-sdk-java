package io.rong.models.message;

import io.rong.models.push.HarmonyOSPlatformNotification;
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
         * The title displayed in the notification bar, with a maximum length of 50 characters.
         */
        private String title;

        /**
         * The content of the push notification.
         */
        private String pushContent;

        /**
         * Set the push notification and additional information for the iOS platform.
         */
        private PlatformNotification ios;

        /**
         * Set the push notification and additional information for the Android platform.
         */
        private PlatformNotification android;

        /**
         * Sets the push notification and additional information for the HarmonyOS platform.
         */
        private HarmonyOSPlatformNotification harmonyOS;

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

        public HarmonyOSPlatformNotification getHarmonyOS() {
            return harmonyOS;
        }

        public void setHarmonyOS(HarmonyOSPlatformNotification harmonyOS) {
            this.harmonyOS = harmonyOS;
        }

        @Override
        public String toString() {
            return GsonUtil.toJson(this, Notification.class);
        }
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PushUserMessage.class);
    }
}
