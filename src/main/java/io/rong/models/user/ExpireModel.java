package io.rong.models.user;

import io.rong.util.GsonUtil;

public class ExpireModel {

    /**
     * 需要设置 Token 失效的用户 ID，支持设置多个最多不超过 20 个。
     */
    private String[]  userId;

    /**
     * 过期时间戳精确到毫秒，该时间戳前用户获取的 Token 全部失效，使用时间戳之前的 Token 已经在连接中的用户不会立即失效，断开后无法进行连接。
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
