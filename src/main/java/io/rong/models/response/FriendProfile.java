package io.rong.models.response;

/**
 * @author RongCloud
 */
public class FriendProfile {
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 用户名
     */
    private String name;
    /**
     * 好友备注名
     */
    private String remarkName;
    /**
     * 自定义扩展属性
     */
    private String friendExtProfile;
    /**
     * 添加时间
     */
    private Long time;
    /**
     * 是否是黑名单
     * 0：否
     * 1：是
     */
    private Integer isBlacklist;
    /**
     * 好友方向类型：
     * 1：单向好友
     * 2：双向好友
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
