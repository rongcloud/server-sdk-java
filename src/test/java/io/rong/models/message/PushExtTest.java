package io.rong.models.message;

import io.rong.util.GsonUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author huhangtao
 * @date 2024/8/8  18:32
 */
public class PushExtTest {

    /**
     * HONOR.importance
     * HONOR.image
     * <p>
     * HW.channelId
     * HW.importance
     * HW.image
     * HW.category
     * <p>
     * MI.channelId
     * MI.large_icon_uri
     * <p>
     * OPPO.channelId
     * <p>
     * VIVO.classification
     * VIVO.category
     * <p>
     * APNs.thread-id
     * APNs.apns-collapse-id
     * APNs.richMediaUri
     * APNs.interruption-level
     * <p>
     * FCM.channelId
     * FCM.collapse_key
     * FCM.imageUrl
     * <p>
     * <p>
     * OHOS.category
     * OHOS.image
     */
    @Test
    public void testBuild() {
        PushExt pushExt = PushExt.build("title", 1,
                new PushExt.HW("channelId", "importance", "image", "category"),
                new PushExt.HONOR("importance", "image"),
                new PushExt.MI("channelId", "large_icon_uri"),
                new PushExt.OPPO("channelId"),
                new PushExt.VIVO("classification", "category"),
                new PushExt.APNs("thread-id", "apns-collapse-id", "richMediaUri", "interruption-level"),
                new PushExt.FCM("channelId", "collapse_key", "imageUrl"),
                new PushExt.OHOS("category", "image")
        );

        String expectStr = "{\"title\":\"title\",\"forceShowPushContent\":1,\"pushConfigs\":[" +
                "{\"HW\":{\"image\":\"image\",\"importance\":\"importance\",\"category\":\"category\",\"channelId\":\"channelId\"}}," +
                "{\"HONOR\":{\"image\":\"image\",\"importance\":\"importance\"}}," +
                "{\"MI\":{\"large_icon_uri\":\"large_icon_uri\",\"channelId\":\"channelId\"}}," +
                "{\"OPPO\":{\"channelId\":\"channelId\"}}," +
                "{\"VIVO\":{\"classification\":\"classification\",\"category\":\"category\"}}," +
                "{\"APNs\":{\"interruption-level\":\"interruption-level\",\"richMediaUri\":\"richMediaUri\",\"apns-collapse-id\":\"apns-collapse-id\",\"thread-id\":\"thread-id\"}}," +
                "{\"FCM\":{\"collapse_key\":\"collapse_key\",\"imageUrl\":\"imageUrl\",\"channelId\":\"channelId\"}}," +
                "{\"OHOS\":{\"image\":\"image\",\"category\":\"category\"}}]}";
        Assert.assertEquals(expectStr, GsonUtil.toJson(pushExt));
    }

}