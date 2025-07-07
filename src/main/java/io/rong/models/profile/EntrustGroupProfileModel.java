package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class EntrustGroupProfileModel {

    private String groupId;
    private String groupName;
    private String owner;
    private String groupProfile;
    private String groupExtProfile;
    private String permissions;
    private String remarkName;
    private Long createTime;
    private Integer memberCount;

    public String getGroupId() {
        return groupId;
    }

    public EntrustGroupProfileModel setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public EntrustGroupProfileModel setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public EntrustGroupProfileModel setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getGroupProfile() {
        return groupProfile;
    }

    public EntrustGroupProfileModel setGroupProfile(String groupProfile) {
        this.groupProfile = groupProfile;
        return this;
    }

    public String getGroupExtProfile() {
        return groupExtProfile;
    }

    public EntrustGroupProfileModel setGroupExtProfile(String groupExtProfile) {
        this.groupExtProfile = groupExtProfile;
        return this;
    }

    public String getPermissions() {
        return permissions;
    }

    public EntrustGroupProfileModel setPermissions(String permissions) {
        this.permissions = permissions;
        return this;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public EntrustGroupProfileModel setRemarkName(String remarkName) {
        this.remarkName = remarkName;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public EntrustGroupProfileModel setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public EntrustGroupProfileModel setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
        return this;
    }
}
