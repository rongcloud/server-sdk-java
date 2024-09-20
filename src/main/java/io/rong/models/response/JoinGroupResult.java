package io.rong.models.response;


import io.rong.util.GsonUtil;


/**
 * @author RongCloud
 */
public class JoinGroupResult extends ResponseResult {

    private String groupId;
    private String name;
    private String owner;
    private String groupProfile;
    private String groupExtProfile;
    private String permissions;
    private String remarkName;
    private Long createTime;
    private Integer memberCount;

    public JoinGroupResult(Integer code, String msg) {
        super(code, msg);
    }

    public String getGroupId() {
        return groupId;
    }

    public JoinGroupResult setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getName() {
        return name;
    }

    public JoinGroupResult setName(String name) {
        this.name = name;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public JoinGroupResult setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getGroupProfile() {
        return groupProfile;
    }

    public JoinGroupResult setGroupProfile(String groupProfile) {
        this.groupProfile = groupProfile;
        return this;
    }

    public String getGroupExtProfile() {
        return groupExtProfile;
    }

    public JoinGroupResult setGroupExtProfile(String groupExtProfile) {
        this.groupExtProfile = groupExtProfile;
        return this;
    }

    public String getPermissions() {
        return permissions;
    }

    public JoinGroupResult setPermissions(String permissions) {
        this.permissions = permissions;
        return this;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public JoinGroupResult setRemarkName(String remarkName) {
        this.remarkName = remarkName;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public JoinGroupResult setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public JoinGroupResult setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, JoinGroupResult.class);
    }
}
