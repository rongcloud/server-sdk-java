package io.rong.models.group;

/**
 * 群特别关注用户数据模型
 *
 * @author Rongcloud
 * */
public class AttentionModel {
    /**
     * 用户id
     **/
    private String userId;

    /**
     * 用户id数组，最多5000个（清理多个群成员特别关注的同一个人关注时使用）
     */
    private String[] userIds;
    /**
     * 群组id
     **/
    private String groupId;

    /**
     * 特别关注用户 ID（清理多个群成员特别关注的同一个人关注时使用）
     **/
    private String attentionUserId;
    /**
     * 特别关注用户 ID 数组(单次最多 100 个用户)
     **/
    private String[] attentionUserIds;

    public AttentionModel() {
    }

    /**
     * 构造方法
     *
     * @param userId 用户id
     * @param groupId 群组id
     */
    public AttentionModel(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public AttentionModel setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public AttentionModel setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getAttentionUserId() {
        return attentionUserId;
    }

    public AttentionModel setAttentionUserId(String attentionUserId) {
        this.attentionUserId = attentionUserId;
        return this;
    }

    public String[] getUserIds() {
        return userIds;
    }

    public void setUserIds(String[] userIds) {
        this.userIds = userIds;
    }

    public String[] getAttentionUserIds() {
        return attentionUserIds;
    }

    public void setAttentionUserIds(String[] attentionUserIds) {
        this.attentionUserIds = attentionUserIds;
    }
}
