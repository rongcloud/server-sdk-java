package io.rong.models.response;


/**
 * @author RongCloud
 */
public class GroupMember {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 群昵称：默认未设置时为用户名称
     */
    private String nickname;
    /**
     * 群身份：
     * 0：普通群成员
     * 1：群主
     * 2：群管理员
     */
    private Integer role;
    /**
     * 加入时间
     */
    private Long time;
    /**
     * 群成员附加信息
     */
    private String extra;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
