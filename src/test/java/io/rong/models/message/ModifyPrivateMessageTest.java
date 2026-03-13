package io.rong.models.message;

import io.rong.messages.TxtMessage;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link ModifyPrivateMessage}.
 */
public class ModifyPrivateMessageTest {

    @Test
    public void testFieldsWithSetter() {
        TxtMessage content = new TxtMessage("updated", "extra");
        ModifyPrivateMessage message = new ModifyPrivateMessage()
                .setSenderId("user1")
                .setTargetId("user2")
                .setMsgUID("5FGT-7VA9-G4DD-4V5P")
                .setContent(content)
                .setObjectName("RC:TxtMsg");

        Assert.assertEquals("user1", message.getSenderId());
        Assert.assertEquals("user2", message.getTargetId());
        Assert.assertEquals("5FGT-7VA9-G4DD-4V5P", message.getMsgUID());
        Assert.assertSame(content, message.getContent());
        Assert.assertEquals("RC:TxtMsg", message.getObjectName());
    }

    @Test
    public void testFieldsWithConstructor() {
        TxtMessage content = new TxtMessage("updated", "extra");
        ModifyPrivateMessage message = new ModifyPrivateMessage("user1", "user2", "uid", content);

        Assert.assertEquals("user1", message.getSenderId());
        Assert.assertEquals("user2", message.getTargetId());
        Assert.assertEquals("uid", message.getMsgUID());
        Assert.assertSame(content, message.getContent());
    }
}
