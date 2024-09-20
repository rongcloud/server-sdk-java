package io.rong.models.response;

/**
 * Author:RongCloud
 */
public class FollowedMember {

    /**
     * 关注成员ID
     */
    private String userId;
    /**
     * 关注时间
     */
    private Long timestamp;

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
}
