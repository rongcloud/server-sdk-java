package io.rong.models.response;

/**
 * @author RongCloud
 */
public class PermissionSetting {
    /**
     * 用户 ID。
     */
    String userId;
    /**
     * 0：未设置：默认，此状态时以 AppKey 设置的加好友权限为准
     * 1：任何人直接添加好友
     * 2：需要用户同意添加好友
     * 3：不允许任何人添加好友
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
