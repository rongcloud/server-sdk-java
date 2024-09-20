package io.rong.models.profile;

import java.util.Map;

/**
 * @author RongCloud
 */
public class EntrustGroupModel {

    /**
     * 群组 ID
     */
    private String groupId;
    /**
     * 群组名称
     */
    private String name;
    /**
     * 群主 ID
     */
    private String owner;

    private Map<String, String> groupProfile;
    private Map<String, String> groupExtProfile;
    private Map<String, Integer> permissions;

    public String getGroupId() {
        return groupId;
    }

    public EntrustGroupModel setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getName() {
        return name;
    }

    public EntrustGroupModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public EntrustGroupModel setOwner(String owner) {
        this.owner = owner;
        return this;
    }


    public Map<String, String> getGroupProfile() {
        return groupProfile;
    }

    public EntrustGroupModel setGroupProfile(Map<String, String> groupProfile) {
        this.groupProfile = groupProfile;
        return this;
    }

    public Map<String, String> getGroupExtProfile() {
        return groupExtProfile;
    }

    public EntrustGroupModel setGroupExtProfile(Map<String, String> groupExtProfile) {
        this.groupExtProfile = groupExtProfile;
        return this;
    }

    public Map<String, Integer> getPermissions() {
        return permissions;
    }

    public EntrustGroupModel setPermissions(Map<String, Integer> permissions) {
        this.permissions = permissions;
        return this;
    }
}
