package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class PagingGetFriendsModel extends PageModel {

    /**
     * 操作用户 ID
     */
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
