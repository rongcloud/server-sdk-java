package io.rong.models.group;

/**
 * 用户群组名称备注名设置模型
 *
 * @author Rongcloud
 * */
public class AliasModel {
    /**
     * 用户id
     **/
    private String userId;
    /**
     * 群组id
     **/
    private String groupId;
    /**
     * 群组备注名
     **/
    private String remarkName;

    public AliasModel() {
    }

    /**
     * 构造方法
     *
     * @param userId 用户id
     * @param groupId 群组id
     */
    public AliasModel(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    /**
     * 构造方法
     *
     * @param userId 用户id
     * @param groupId 群组id
     * @param remarkName 群组备注名
     */
    public AliasModel(String userId, String groupId, String remarkName) {
        this.userId = userId;
        this.groupId = groupId;
        this.remarkName = remarkName;
    }

    public String getUserId() {
        return userId;
    }

    public AliasModel setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public AliasModel setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public AliasModel setRemarkName(String remarkName) {
        this.remarkName = remarkName;
        return this;
    }
}
