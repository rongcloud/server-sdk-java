package io.rong.models.response;

/**
 * Author: RongCloud
 */
public class FollowedMember {

    /**
     * Member ID of the followed user
     */
    private String userId;
    /**
     * Timestamp when the user was followed
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
