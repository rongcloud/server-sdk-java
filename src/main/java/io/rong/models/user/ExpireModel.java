package io.rong.models.user;

import io.rong.util.GsonUtil;

public class ExpireModel {

    /**
     * The user IDs for which the Token needs to be invalidated. Supports up to 20 user IDs.
     */
    private String[] userId;

    /**
     * The expiration timestamp in milliseconds. Tokens obtained before this timestamp will be invalidated. Users already connected with a Token obtained before this timestamp will not be immediately disconnected but will be unable to reconnect after disconnection.
     */
    private long time;

    public String[] getUserId() {
        return userId;
    }

    public ExpireModel setUserId(String[] userId) {
        this.userId = userId;
        return this;
    }

    public Long getTime() {
        return time;
    }

    public ExpireModel setTime(long time) {
        this.time = time;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ExpireModel.class);
    }
}
