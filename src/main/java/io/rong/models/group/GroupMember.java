package io.rong.models.group;

import io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class GroupMember {
    /**
     * Group member ID.
     */
    public String id;
    /**
     * Group ID.
     */
    public String groupId;
    /**
     * Mute duration in minutes.
     */
    public Integer munite;

    public GroupMember() {
        super();
    }

    public GroupMember(String id, String groupId, Integer munite) {
        this.id = id;
        this.groupId = groupId;
        this.munite = munite;
    }

    /**
     * @param id Set the ID.
     */
    public GroupMember setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the ID.
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Get the group ID
     *
     * @return String
     */
    public String getGroupId() {
        return this.groupId;
    }
    /**
     * @param groupId Set the group ID
     *
     *
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * Get the mute duration
     *
     * @return String
     */
    public Integer getMunite() {
        return this.munite;
    }
    /**
     * @param munite Set the mute duration
     *
     *
     */
    public void setMunite(Integer munite) {
        this.munite = munite;
    }
    @Override
    public String toString() {
        return GsonUtil.toJson(this, GroupMember.class);
    }

}
