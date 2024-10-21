package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class QueryJoinedGroupsModel extends PageModel {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 群组身份类型：
     * 0：全部（默认）
     * 1：群主
     * 2：群管理员
     * 3：普通群成员
     */
    private Integer role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
