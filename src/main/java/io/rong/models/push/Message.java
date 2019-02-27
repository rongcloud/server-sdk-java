package io.rong.models.push;

import io.rong.util.GsonUtil;

/**
 * 发送消息内容（必传）
 * 
 * @author jiangxinjun
 * @date 2019-02-27
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

    @Override
    public String toString() {
        return GsonUtil.toJson(this, Message.class);
    }
}
