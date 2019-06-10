package io.rong.models.push;

import io.rong.util.GsonUtil;

/**
 * 推送消息体
 */
public class PushModel extends BroadcastPushPublicPart {

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PushModel.class);
    }
}