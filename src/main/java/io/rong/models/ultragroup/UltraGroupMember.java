package io.rong.models.ultragroup;

import io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class UltraGroupMember {
    /**
     * 群组成员Id。
     * */
    public String id;
    /**
     * 群组ID
     * */
    public String groupId;

    public UltraGroupMember() {
        super();
    }

    public UltraGroupMember(String id, String groupId) {
        this.id = id;
        this.groupId = groupId;
    }

    /**
     * @param id 设置id
     *
     */
    public UltraGroupMember setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 获取id
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * 获取groupId
     *
     * @return String
     */
    public String getGroupId() {
        return this.groupId;
    }
    /**
     * @param groupId 设置群组id
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
