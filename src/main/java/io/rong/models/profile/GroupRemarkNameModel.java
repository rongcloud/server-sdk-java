package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class GroupRemarkNameModel {

    /**
     * Group ID
     */
    private String groupId;
    /**
     * User ID
     */
    private String userId;
    /**
     * Group member nickname
     */
    private String remarkName;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

}
