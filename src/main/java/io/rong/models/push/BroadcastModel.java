package io.rong.models.push;

import io.rong.util.GsonUtil;

/**
 * Broadcast message body.
 */
public class BroadcastModel extends BroadcastPushPublicPart {

    /**
     * The sender's user ID. (Required)
     */
    private String fromuserid;

    /**
     * The message content to be sent. (Required)
     */
    private Message message;

    public String getFromuserid() {
        return fromuserid;
    }

    public void setFromuserid(String fromuserid) {
        this.fromuserid = fromuserid;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, BroadcastModel.class);
    }
}