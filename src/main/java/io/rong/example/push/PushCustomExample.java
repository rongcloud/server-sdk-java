package io.rong.example.push;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.models.push.*;
import io.rong.models.response.PushResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 推送plus Demo class
 * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 *
 * @author Lang
 */
public class PushCustomExample {
    /**
     * 此处替换成您的appKey
     */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     */
    private static final String appSecret = "appSecret";
    /**
     * 自定义api地址
     */
    private static final String api = "http://api-cn.rongcloud.com";


    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
        PushModel pushmodel = new PushModel();
        pushmodel.setPlatform(new String[]{"ios", "android"});
        AudienceCustom audience = new AudienceCustom();
        audience.setIs_to_all(false);
        AudienceCustom.TagItem at1 = new AudienceCustom.TagItem();
        AudienceCustom.TagItem at2 = new AudienceCustom.TagItem();
        AudienceCustom.TagItem at3 = new AudienceCustom.TagItem();

        //代表逻辑：((beijing or shanghai) and (男 or 女)) OR (not nerd)

        at1.setTags(new String[]{"beijing", "shanghai"});
        at1.setTagsOperator(AudienceCustom.AND);

        at2.setTags(new String[]{"男", "女"});
        at2.setTagsOperator(AudienceCustom.OR);
        at2.setItemsOperator(AudienceCustom.AND);

        at3.setTags(new String[]{"nerd"});
        at3.setIsNot(true);
        at3.setItemsOperator(AudienceCustom.OR);

        audience.setTagItems(at1, at2, at3);
        pushmodel.setAudience(audience);

        Notification notification = new Notification();
        notification.setAlert("this is push");
        PlatformNotification platformNotification = new PlatformNotification();
        String hwChannelId = "23423423423";
        PlatformNotification.Platform ppHw = new PlatformNotification.Platform(hwChannelId);
        ppHw.setImage("http://erjijl.jpg");
        platformNotification.setHw(ppHw);
        PlatformNotification.Platform ppHonor = new PlatformNotification.Platform();
        ppHonor.setImportance("importance");
        ppHonor.setImage("http://erjijl.jpg");
//        platformNotification.setHonor(ppHonor);
        platformNotification.setHonor("importance", "http://erjijl.jpg");

        platformNotification.setOppo("oppochannelid");

        notification.setAndroid(platformNotification);
        pushmodel.setNotification(notification);

        HarmonyOSPlatformNotification harmonyOSPlatformNotification = new HarmonyOSPlatformNotification();
        harmonyOSPlatformNotification.setAlert("alert");
        Map<String, String> extras = new HashMap<>();
        extras.put("key", "value");
        harmonyOSPlatformNotification.setExtras(extras);
        HarmonyOSPlatformNotification.HarmonyOSSettings harmonyOSSettings = new HarmonyOSPlatformNotification.HarmonyOSSettings();
        harmonyOSSettings.setCategory("category");
        harmonyOSSettings.setImage("http://erjijl.jpg");
        harmonyOSPlatformNotification.setOhos(harmonyOSSettings);
        notification.setHarmonyOS(harmonyOSPlatformNotification);

        System.out.println("req: " + pushmodel.toString());
        PushResult result = rongCloud.pushCustom.pushcustom(pushmodel);
        System.out.println("rsp: " + result.toString());
    }
}
