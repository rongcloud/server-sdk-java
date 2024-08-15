package io.rong.models.push;

import io.rong.util.GsonUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author huhangtao
 * @date 2024/8/8  18:43
 */
public class PlatformNotificationTest {

    @Test
    public void testBuild() {
        PlatformNotification platformNotification = new PlatformNotification();
        platformNotification.setInterruptionLevel("passive");
        platformNotification.setThreadId("thread-id");
        PlatformNotification.Platform platform = new PlatformNotification.Platform();
        platform.setCategory("test");
        platform.setCollapseKey("collapse_key");
        platform.setLargeIconUri("large_icon_uri");
        platformNotification.setHw(platform);
        Assert.assertEquals("{\"thread-id\":\"thread-id\",\"interruption-level\":\"passive\",\"hw\":{\"large_icon_uri\":\"large_icon_uri\",\"collapse_key\":\"collapse_key\",\"category\":\"test\"}}", GsonUtil.toJson(platformNotification));
    }
}