package io.rong.models.response.group;

import io.rong.util.GsonUtil;
/**
 * 用户指定群组特别关注成员实体
 */
public class AttentionMembers {

    // 用户 Id。
    String userId;
    // 被关注时间
    Long timestamp;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, AttentionMembers.class);
    }
}
