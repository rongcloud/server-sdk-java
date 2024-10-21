package io.rong.models.response;

/**
 * @author RongCloud
 */
public class UserProfile {
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 用户托管信息版本号
     */
    private Long version;
    /**
     * 用户基本信息
     */
    private String userProfile;
    /**
     * 自定义扩展属性
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
