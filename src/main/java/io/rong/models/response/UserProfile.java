package io.rong.models.response;

/**
 * @author RongCloud
 */
public class UserProfile {
    /**
     * User ID
     */
    private String userId;
    /**
     * Version number of the user's hosted information
     */
    private Long version;
    /**
     * Basic user information
     */
    private String userProfile;
    /**
     * Custom extended attributes
     */
    private String userExtProfile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserExtProfile() {
        return userExtProfile;
    }

    public void setUserExtProfile(String userExtProfile) {
        this.userExtProfile = userExtProfile;
    }
}
