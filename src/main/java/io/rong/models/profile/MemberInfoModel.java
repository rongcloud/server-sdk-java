package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class MemberInfoModel {

    /**
     * 群组id
     */
    private String groupId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 群成员昵称
     */
    private String nickname;
    /**
     * 附加信息
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
