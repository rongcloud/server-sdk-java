package io.rong.models.message;

import io.rong.messages.TxtMessage;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link ModifyGroupMessage}.
 */
public class ModifyGroupMessageTest {

    @Test
    public void testFieldsWithSetter() {
        TxtMessage content = new TxtMessage("updated", "extra");
        ModifyGroupMessage message = new ModifyGroupMessage()
                .setSenderId("user1")
                .setGroupId("group1")
                .setMsgUID("5FGT-7VA9-G4DD-4V5P")
                .setContent(content)
                .setObjectName("RC:TxtMsg");

        Assert.assertEquals("user1", message.getSenderId());
        Assert.assertEquals("group1", message.getGroupId());
        Assert.assertEquals("5FGT-7VA9-G4DD-4V5P", message.getMsgUID());
        Assert.assertSame(content, message.getContent());
        Assert.assertEquals("RC:TxtMsg", message.getObjectName());
    }

    @Test
    public void testFieldsWithConstructor() {
        TxtMessage content = new TxtMessage("updated", "extra");
        ModifyGroupMessage message = new ModifyGroupMessage("user1", "group1", "uid", content);

        Assert.assertEquals("user1", message.getSenderId());
        Assert.assertEquals("group1", message.getGroupId());
        Assert.assertEquals("uid", message.getMsgUID());
        Assert.assertSame(content, message.getContent());
    }
}
