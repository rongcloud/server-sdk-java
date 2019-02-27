package io.rong.models.push;

import io.rong.util.GsonUtil;

/**
 * 广播消息体。
 */
public class BroadcastModel extends BroadcastPushPublicPart {
    
    /**
     *  发送人用户 Id。（必传）
     */
    private String fromuserid;
    
    /**
     * 发送消息内容（必传）
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
