package io.rong.models.ultragroup;

import io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class UltraGroupMember {
    /**
     * The ID of the group member.
     */
    public String id;
    /**
     * The ID of the group.
     */
    public String groupId;

    public UltraGroupMember() {
        super();
    }

    public UltraGroupMember(String id, String groupId) {
        this.id = id;
        this.groupId = groupId;
    }

    /**
     * @param id Sets the ID of the group member.
     *
     */
    public UltraGroupMember setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Gets the ID of the group member.
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the ID of the group.
     *
     * @return String
     */
    public String getGroupId() {
        return this.groupId;
    }
    /**
     * @param groupId Sets the group ID
     *
     *
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, UltraGroupMember.class);
    }

}
