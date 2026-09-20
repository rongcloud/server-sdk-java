package io.rong.models.push;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.rong.util.GsonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

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
        platform.setNotifyLevel(1);
        Assert.assertTrue( GsonUtil.toJson(platformNotification).contains("\"notify_level\":1"));
    }

    @Test
    public void testMiTemplateParamBuild() {
        PlatformNotification platformNotification = new PlatformNotification();
        PlatformNotification.Platform mi = new PlatformNotification.Platform();
        Map<String, String> templateParam = new LinkedHashMap<String, String>();
        templateParam.put("keywords1", "sender");
        templateParam.put("keywords2", "content");

        mi.setChannelId("channelId");
        mi.setTemplateId("templateId");
        mi.setTemplateParam(templateParam);
        platformNotification.setMi(mi);

        JsonObject miJson = new JsonParser()
                .parse(GsonUtil.toJson(platformNotification))
                .getAsJsonObject()
                .getAsJsonObject("mi");
        Assert.assertEquals("channelId", miJson.get("channelId").getAsString());
        Assert.assertEquals("templateId", miJson.get("templateId").getAsString());
        Assert.assertTrue(miJson.get("templateParam").isJsonObject());
        Assert.assertEquals("sender", miJson.getAsJsonObject("templateParam").get("keywords1").getAsString());
        Assert.assertEquals("content", miJson.getAsJsonObject("templateParam").get("keywords2").getAsString());
    }
}
