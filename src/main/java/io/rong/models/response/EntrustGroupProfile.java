package io.rong.models.response;


/**
 * @author RongCloud
 */
public class EntrustGroupProfile {
    private String groupId;
    private String name;
    private String remarkName;
    private String groupProfile;
    private Long createTime;
    private String permissions;
    private String groupExtProfile;
    private Long joinTime;
    private Integer role;
    private Integer count;
    private String owner;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public EntrustGroupProfile setName(String name) {
        this.name = name;
        return this;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }


    public String getGroupProfile() {
        return groupProfile;
    }

    public void setGroupProfile(String groupProfile) {
        this.groupProfile = groupProfile;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getGroupExtProfile() {
        return groupExtProfile;
    }

    public void setGroupExtProfile(String groupExtProfile) {
        this.groupExtProfile = groupExtProfile;
    }

    public Long getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Long joinTime) {
        this.joinTime = joinTime;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
