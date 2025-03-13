package io.rong.models.response;

/**
 * @author RongCloud
 */
public class PermissionSetting {
    /**
     * User ID.
     */
    String userId;
    /**
     * 0: Not set (default), in this state, the friend request permission set at the App Key level applies
     * 1: Allow anyone to add as a friend directly
     * 2: Require user approval to add as a friend
     * 3: Do not allow anyone to add as a friend
     */
    String type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
