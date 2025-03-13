package io.rong.models.push;

import io.rong.util.GsonUtil;

/**
 * Push notification message body
 */
public class PushModel extends BroadcastPushPublicPart {

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PushModel.class);
    }
}