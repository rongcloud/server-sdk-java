package io.rong.models.response;

/**
 * @author RongCloud
 */
public class FriendProfile {
    /**
     * User ID
     */
    private String userId;
    /**
     * Username
     */
    private String name;
    /**
     * Friend alias
     */
    private String remarkName;
    /**
     * Custom extended attributes
     */
    private String friendExtProfile;
    /**
     * Timestamp when the friend was added
     */
    private Long time;
    /**
     * Indicates whether the user is in the blocklist
     * 0: No
     * 1: Yes
     */
    private Integer isBlacklist;
    /**
     * Friend direction type:
     * 1: One-way friend
     * 2: Two-way friend
     */
    private Integer directionType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getFriendExtProfile() {
        return friendExtProfile;
    }

    public void setFriendExtProfile(String friendExtProfile) {
        this.friendExtProfile = friendExtProfile;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getIsBlacklist() {
        return isBlacklist;
    }

    public void setIsBlacklist(Integer isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    public Integer getDirectionType() {
        return directionType;
    }

    public void setDirectionType(Integer directionType) {
        this.directionType = directionType;
    }
}
