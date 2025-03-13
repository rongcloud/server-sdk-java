package io.rong.models.message;

import io.rong.models.push.HarmonyOSPlatformNotification;
import io.rong.models.push.PlatformNotification;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huhangtao
 */
public class PushUserMessageTest {
    @Test
    public void testBuild() {
        PushUserMessage pushUserMessage = new PushUserMessage();

        List<String> users = new ArrayList<>();
        users.add("user1");
        pushUserMessage.setUserIds(users);

        PushUserMessage.Notification notification = new PushUserMessage.Notification();
        pushUserMessage.setNotification(notification);
        notification.setTitle("this is push");

        // - Set HarmonyOS push notification
        HarmonyOSPlatformNotification harmonyOS = new HarmonyOSPlatformNotification();
        harmonyOS.setAlert("alert");
        Map<String, String> extras = new HashMap<>();
        extras.put("key", "value");
        harmonyOS.setExtras(extras);
        HarmonyOSPlatformNotification.HarmonyOSSettings ohos = new HarmonyOSPlatformNotification.HarmonyOSSettings();
        ohos.setCategory("category");
        ohos.setImage("http://erjijl.jpg");
        harmonyOS.setOhos(ohos);
        notification.setHarmonyOS(harmonyOS);

        // - Set Android push notification
        PlatformNotification anroid = new PlatformNotification();
        anroid.setExtras(extras);
        PlatformNotification.Platform platform = new PlatformNotification.Platform();
        platform.setCategory("test");
        platform.setCollapseKey("collapse_key");
        platform.setLargeIconUri("large_icon_uri");
        anroid.setHw(platform);
        anroid.setAlert("alert");
        notification.setAndroid(anroid);


        // - Set up iOS push notification
        PlatformNotification ios = new PlatformNotification();
        ios.setInterruptionLevel("passive");
        ios.setThreadId("thread_id");
        ios.setApnsCollapseId("apns_collapse_id");
        notification.setIos(ios);

        Assert.assertEquals("{\"userIds\":[\"user1\"]," +
                        "\"notification\":{\"title\":\"this is push\"," +
                        "\"ios\":{\"thread-id\":\"thread_id\",\"apns-collapse-id\":\"apns_collapse_id\",\"interruption-level\":\"passive\"}," +
                        "\"android\":{\"alert\":\"alert\",\"extras\":{\"key\":\"value\"},\"hw\":{\"large_icon_uri\":\"large_icon_uri\",\"collapse_key\":\"collapse_key\",\"category\":\"test\"}}," +
                        "\"harmonyOS\":{\"alert\":\"alert\",\"extras\":{\"key\":\"value\"},\"ohos\":{\"image\":\"http://erjijl.jpg\",\"category\":\"category\"}}}}",
                pushUserMessage.toString());
    }

}