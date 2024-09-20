package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class PagingGetFriendsModel extends PageModel {

    /**
     * 操作用户 ID
     */
    private String userId;
    /**
     * 好友方向类型：
     * 0：全部（默认）
     * 1：单向好友
     * 2：双向好友
     */
    private Integer directionType = 0;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getDirectionType() {
        return directionType;
    }

    public void setDirectionType(Integer directionType) {
        this.directionType = directionType;
    }
}
