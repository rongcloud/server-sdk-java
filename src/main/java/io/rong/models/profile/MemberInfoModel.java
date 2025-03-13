package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class MemberInfoModel {

    /**
     * Group ID
     */
    private String groupId;
    /**
     * User ID
     */
    private String userId;
    /**
     * Group member nickname
     */
    private String nickname;
    /**
     * Additional information
     */
    private String extra;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

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

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
