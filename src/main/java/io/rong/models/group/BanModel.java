package io.rong.models.group;

/**
 * 群全体禁言/解禁实体
 *
 * @author Rongcloud
 */
public class BanModel {

    /**
     * 群组 ID
     */
    private String[] groupIds;
    /**
     * 禁言白名单用户列表。最多不超过20个。
     * 注：只有groupId数组长度为1时，此参数有效
     */
    private String[] whiteUserIds;
    /**
     * 是否清空群禁言成员列表(群设置全体禁言时使用)：
     * 默认为 0 不清空，设置 1 时清空该群组中通过“/group/user/gag/add.json” 接口设置的群成员禁言列表
     */
    private Integer isClearBanUser = 0;
    /**
     * 是否清空群禁言白名单成员列表(取消群全体禁言时使用)：
     * 默认为 0 不清空；
     * 设置为 1 时清空该群组退通过“/group/user/ban/whitelist/add.json”接口设置的群禁言白名单列表
     */
    private Integer isClearWhiteUser = 0;


    public BanModel() {
    }

    public BanModel(String[] groupIds) {
        this.groupIds = groupIds;
    }

    public BanModel(String[] groupIds, String[] whiteUserIds) {
        this.groupIds = groupIds;
        this.whiteUserIds = whiteUserIds;
    }

    public BanModel(String[] groupIds, String[] whiteUserIds, Integer isClearBanUser) {
        this.groupIds = groupIds;
        this.whiteUserIds = whiteUserIds;
        this.isClearBanUser = isClearBanUser;
    }

    public String[] getGroupIds() {
        return groupIds;
    }

    public BanModel setGroupIds(String[] groupIds) {
        this.groupIds = groupIds;
        return this;
    }

    public String[] getWhiteUserIds() {
        return whiteUserIds;
    }

    public BanModel setWhiteUserIds(String[] whiteUserIds) {
        this.whiteUserIds = whiteUserIds;
        return this;
    }

    public Integer getIsClearBanUser() {
        return isClearBanUser;
    }

    public BanModel setIsClearBanUser(Integer isClearBanUser) {
        this.isClearBanUser = isClearBanUser;
        return this;
    }

    public Integer getIsClearWhiteUser() {
        return isClearWhiteUser;
    }

    public BanModel setIsClearWhiteUser(Integer isClearWhiteUser) {
        this.isClearWhiteUser = isClearWhiteUser;
        return this;
    }
}
