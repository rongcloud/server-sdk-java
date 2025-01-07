package io.rong.models.push;

import io.rong.util.GsonUtil;

/**
 * 发送消息内容（必传）
 */
public class Message {

    /**
     * 发送消息内容，参考融云 Server API 消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）
     */
    private String content;

    /**
     * 消息类型，参考融云 Server API 消息类型表.消息标志；可自定义消息类型，长度不超过 32 个字符。（必传）
     */
    private String objectName;

    /**
     * 禁止更新会话最后一条消息。 当该参数为 false 时，发送的该条消息都会进入会话列表; 为 true 时，不会更新到会话列表的消息内容。
     * 注：此参数仅对存储在客户端的消息有效。
     */
    private Boolean disableUpdateLastMsg;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }


    public Boolean getDisableUpdateLastMsg() {
        return disableUpdateLastMsg;
    }

    public Message setDisableUpdateLastMsg(Boolean disableUpdateLastMsg) {
        this.disableUpdateLastMsg = disableUpdateLastMsg;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, Message.class);
    }
}