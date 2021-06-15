package io.rong.models.user;

import io.rong.util.GsonUtil;

public class BanModel {

    /**
     * 被禁言用户 Id，支持批量设置，最多不超过 1000 个。
     */
    private String[]  userId;
    /**
     * 禁言状态，0 解除禁言、1 添加禁言
     */
    private Integer  state;
    /**
     * 会话类型，目前支持单聊会话 PERSON
     */
    private String  type;

    public String[] getUserId() {
        return userId;
    }

    public BanModel setUserId(String[] userId) {
        this.userId = userId;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public BanModel setState(int state) {
        this.state = state;
        return this;
    }

    public String getType() {
        return type;
    }

    public BanModel setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, BanModel.class);
    }
}
