package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class UserProfileModel {
    /**
     * Target ID
     */
    private String userId;
    /**
     * User basic information
     * JSON data, the length of KV pairs is configurable, with a default maximum of 20.
     */
    private String userProfile;
    /**
     * Extended user information
     * JSON data, the length of the key should not exceed 32 characters, and the key must be prefixed with 'ext_'. The length of the value should not exceed 256 characters. The length of KV pairs is configurable, with a default maximum of 20.
     */
    private String userExtProfile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
