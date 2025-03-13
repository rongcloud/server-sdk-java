package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class FriendProfileModel {
    /**
     * Operation user ID
     */
    private String userId;
    /**
     * Target user ID, the user ID to be added as a friend
     */
    private String targetId;
    /**
     * Friend remark name
     */
    private String remarkName;
    /**
     * Extended Information: Developers can add custom extended attributes (Key, Value) according to their business needs. By default, up to 10 extended information items can be set.
     * Key is limited to 32 characters, including uppercase and lowercase letters, numbers, and '_', with the format fixed as ext_xxxxx.
     * Value is limited to 256 characters, with no restrictions.
     * Example: {"ext_key1":"value1","ext_key2":"value2"}
     */
    private String friendExtProfile;

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
}
