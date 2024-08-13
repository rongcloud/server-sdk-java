package io.rong.messages;


import io.rong.util.GsonUtil;
import org.junit.Assert;
import org.junit.Test;


/**
 * @author huhangtao
 * @date 2024/8/8  16:48
 */
public class GifMessageTest {

    @Test
    public void test() throws Exception {
        GifMessage gifMessage = new GifMessage(null, null, null, null, null);
        gifMessage.setExtra("ext");
        Assert.assertEquals("{\"extra\":\"ext\"}", GsonUtil.toJson(gifMessage));
    }
}