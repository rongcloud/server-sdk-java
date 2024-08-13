package io.rong.models.response;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * push 返回结果
 */
public class MessageResult extends ResponseResult {

    /**
     * 单聊、群聊、聊天室、超级群消息ID列表,
     */
    private List<MessageUIDEntry> messageUIDs;

    public MessageResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }


    public List<MessageUIDEntry> getMessageUIDs() {
        return messageUIDs;
    }

    public MessageResult setMessageUIDs(List<MessageUIDEntry> messageUIDs) {
        this.messageUIDs = messageUIDs;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, MessageResult.class);
    }


}