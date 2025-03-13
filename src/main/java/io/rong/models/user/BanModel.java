package io.rong.models.user;

import io.rong.util.GsonUtil;

public class BanModel {

    /**
     * The ID of the banned user. Supports batch setting, with a maximum of 1000 users.
     */
    private String[] userId;
    /**
     * The ban status: 0 to unban, 1 to ban.
     */
    private Integer state;
    /**
     * The conversation type, currently supports one-to-one chat PERSON.
     */
    private String type;

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
