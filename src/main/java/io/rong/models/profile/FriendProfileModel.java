package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class FriendProfileModel {
    /**
     * 操作用户 ID
     */
    private String userId;
    /**
     * 目标用户 ID，需要添加为好友的用户 ID
     */
    private String targetId;
    /**
     * 好友备注名
     */
    private String remarkName;
    /**
     * 扩展信息：开发者可根据自身业务需求添加自定义扩展属性（Key、Value），默认最多可设置 10 个扩展信息。
     * key限制 32 个字符， 大小写+数字 + '_', 格式固定 ext_xxxxx
     * value 限制 256 ，无限制
     * 示例：{\"ext_key1\":\"value1\",\"ext_key2\":\"value2\"}
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
