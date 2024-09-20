package io.rong.models.profile;

/**
 * @author RongCloud
 */
public class QuitEntrustGroupModel {

    /**
     * 群组 ID
     */
    private String groupId;
    private String[] userIds;
    private Integer isDelBan;
    private Integer isDelWhite;
    private Integer isDelFollowed;


    public String getGroupId() {
        return groupId;
    }

    public QuitEntrustGroupModel setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String[] getUserIds() {
        return userIds;
    }

    public QuitEntrustGroupModel setUserIds(String[] userIds) {
        this.userIds = userIds;
        return this;
    }

    public Integer getIsDelBan() {
        return isDelBan;
    }

    public QuitEntrustGroupModel setIsDelBan(Integer isDelBan) {
        this.isDelBan = isDelBan;
        return this;
    }

    public Integer getIsDelWhite() {
        return isDelWhite;
    }

    public QuitEntrustGroupModel setIsDelWhite(Integer isDelWhite) {
        this.isDelWhite = isDelWhite;
        return this;
    }

    public Integer getIsDelFollowed() {
        return isDelFollowed;
    }

    public QuitEntrustGroupModel setIsDelFollowed(Integer isDelFollowed) {
        this.isDelFollowed = isDelFollowed;
        return this;
    }
}
