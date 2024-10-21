package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class PagingQueryMembersModel extends PageModel {

    private String groupId;
    private Integer type;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
