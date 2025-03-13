package io.rong.models.response;


/**
 * @author RongCloud
 */
public class GroupMember {
    /**
     * User ID
     */
    private String userId;
    /**
     * Group alias: defaults to the user's name if not set
     */
    private String nickname;
    /**
     * Group role:
     * 0: Regular group member
     * 1: Group owner
     * 2: Group administrator
     */
    private Integer role;
    /**
     * Join time
     */
    private Long time;
    /**
     * Additional information for group member
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
