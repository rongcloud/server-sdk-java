package io.rong.models.push;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huhangtao
 */
public class BroadcastModelTest {

    @Test
    public void testBuild() {
        BroadcastModel broadcast = new BroadcastModel();
        broadcast.setFromuserid("fromuserid");
        broadcast.setPlatform(new String[]{"ios", "android"});
        Audience audience = new Audience();
        audience.setUserid(new String[]{"userid1", "userid2"});
        broadcast.setAudience(audience);
        Message message = new Message();
        message.setContent("this is message");
        message.setObjectName("RC:TxtMsg");
        broadcast.setMessage(message);


        // 推送设置
        Notification notification = new Notification();
        notification.setAlert("this is broadcast");
        broadcast.setNotification(notification);

        // - 设置鸿蒙推送
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


        // - 设置安卓推送
        PlatformNotification anroid = new PlatformNotification();
        anroid.setExtras(extras);
        PlatformNotification.Platform platform = new PlatformNotification.Platform();
        platform.setCategory("test");
        platform.setCollapseKey("collapse_key");
        platform.setLargeIconUri("large_icon_uri");
        anroid.setHw(platform);
        notification.setAndroid(anroid);

        // - 设置 ios 推送
        PlatformNotification ios = new PlatformNotification();
        ios.setInterruptionLevel("passive");
        ios.setThreadId("thread_id");
        ios.setApnsCollapseId("apns_collapse_id");
        notification.setIos(ios);

        Assert.assertEquals("{\"fromuserid\":\"fromuserid\"," +
                        "\"message\":{\"content\":\"this is message\",\"objectName\":\"RC:TxtMsg\"}," +
                        "\"platform\":[\"ios\",\"android\"]," +
                        "\"audience\":{\"tag\":[],\"userid\":[\"userid1\",\"userid2\"]}," +
                        "\"notification\":{\"alert\":\"this is broadcast\"," +
                        "\"ios\":{\"thread-id\":\"thread_id\",\"apns-collapse-id\":\"apns_collapse_id\",\"interruption-level\":\"passive\"}," +
                        "\"android\":{\"extras\":{\"key\":\"value\"},\"hw\":{\"large_icon_uri\":\"large_icon_uri\",\"collapse_key\":\"collapse_key\",\"category\":\"test\"}}," +
                        "\"harmonyOS\":{\"alert\":\"alert\",\"extras\":{\"key\":\"value\"},\"ohos\":{\"image\":\"http://erjijl.jpg\",\"category\":\"category\"}}}}",
                broadcast.toString());
    }

}