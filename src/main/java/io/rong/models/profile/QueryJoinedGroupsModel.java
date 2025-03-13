package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class QueryJoinedGroupsModel extends PageModel {

    /**
     * User ID
     */
    private String userId;
    /**
     * Group role type:
     * 0: All (default)
     * 1: Group Owner
     * 2: Group Administrator
     * 3: Regular Group Member
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
