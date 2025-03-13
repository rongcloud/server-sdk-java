package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class FriendModel {
    /**
     * Operator user ID
     */
    private String userId;
    /**
     * Target user ID, the user ID to be added as a friend
     */
    private String targetId;
    /**
     * Operation type:
     * 1: Target user setting (default): The target user can choose to add directly or require verification
     * 2: Directly add friend: No friend request is sent, no target user consent is required, directly add the other party as a friend
     */
    private Integer optType;
    /**
     * Friend direction type:
     * 1: One-way friend
     * 2: Two-way friend (default)
     */
    private Integer directionType;
    /**
     * Optional, additional information sent with the friend request, length not exceeding 128 characters
     */
    private String extra;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public Integer getOptType() {
        return optType;
    }

    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    public Integer getDirectionType() {
        return directionType;
    }

    public void setDirectionType(Integer directionType) {
        this.directionType = directionType;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
