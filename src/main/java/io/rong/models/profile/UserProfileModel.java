package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class UserProfileModel {
    /**
     * 操作用户 ID
     */
    private String userId;
    /**
     * 用户基本信息
     * json数据，KV的长度有配置，默认最多20个。
     */
    private String userProfile;
    /**
     * 用户扩展信息
     * json数据，key的长度不超过 32 个字符，key的前缀必须加ext_， value的长度，value不超过 256 个字符，KV的长度有配置，默认最多20个。
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
