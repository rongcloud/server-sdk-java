package io.rong.models.profile;

/**
 * @author RongCloud
 */
public class TransferOwnerModel {
    private String groupId;
    private String newOwner;
    private Integer isDelBan;
    private Integer isDelWhite;
    private Integer isDelFollowed;
    private Integer isQuit;

    public String getGroupId() {
        return groupId;
    }

    public TransferOwnerModel setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getNewOwner() {
        return newOwner;
    }

    public TransferOwnerModel setNewOwner(String newOwner) {
        this.newOwner = newOwner;
        return this;
    }

    public Integer getIsDelBan() {
        return isDelBan;
    }

    public TransferOwnerModel setIsDelBan(Integer isDelBan) {
        this.isDelBan = isDelBan;
        return this;
    }

    public Integer getIsDelWhite() {
        return isDelWhite;
    }

    public TransferOwnerModel setIsDelWhite(Integer isDelWhite) {
        this.isDelWhite = isDelWhite;
        return this;
    }

    public Integer getIsDelFollowed() {
        return isDelFollowed;
    }

    public TransferOwnerModel setIsDelFollowed(Integer isDelFollowed) {
        this.isDelFollowed = isDelFollowed;
        return this;
    }

    public Integer getIsQuit() {
        return isQuit;
    }

    public TransferOwnerModel setIsQuit(Integer isQuit) {
        this.isQuit = isQuit;
        return this;
    }
}
