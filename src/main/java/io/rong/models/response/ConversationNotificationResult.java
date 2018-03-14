package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

public class ConversationNotificationResult extends Result {

    //消息免打扰设置状态，0 表示为关闭，1 表示为开启。
    Integer isMuted;

    public ConversationNotificationResult(Integer code, Integer isMuted, String errorMessage) {
        super(code,errorMessage);
        this.code = code;
        this.isMuted = isMuted;
        this.msg = errorMessage;
    }

    /**
     * 设置code
     *
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取code
     *
     * @return Integer
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 获取Muted
     *
     * @return String
     */
    public Integer getIsMuted() {
        return this.isMuted;
    }
    /**
     * 设置Muted状态
     *
     */
    public void setIsMuted(Integer isMuted) {
        this.isMuted = isMuted;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ConversationNotificationResult.class);
    }
}
