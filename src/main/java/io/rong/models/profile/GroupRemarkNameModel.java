package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class GroupRemarkNameModel {

    /**
     * 群组id
     */
    private String groupId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 群成员昵称
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
