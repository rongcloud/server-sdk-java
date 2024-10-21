package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class FriendModel {
    /**
     * 操作用户 ID
     */
    private String userId;
    /**
     * 目标用户 ID，需要添加为好友的用户 ID
     */
    private String targetId;
    /**
     * 添加类型：
     * 1：目标用户设置（默认）：，目标用户可设置为直接添加好友或需要验证同意
     * 2：直接添加好友：不发送好友邀请，不需要目标用户同意，直接添加对方为好友
     */
    private Integer optType;
    /**
     * 好友方向类型：
     * 1：单向好友
     * 2：双向好友（默认）
     */
    private Integer directionType;
    /**
     * 非必填项，发送好友请求时的附加信息，长度不超过 128 个字符
     */
    private String extra;


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

    public Integer getOptType() {
        return optType;
    }

    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    public Integer getDirectionType() {
        return directionType;
    }

    public void setDirectionType(Integer directionType) {
        this.directionType = directionType;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
