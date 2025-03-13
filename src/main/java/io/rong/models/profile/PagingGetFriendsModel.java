package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class PagingGetFriendsModel extends PageModel {

    /**
     * The ID of the user performing the operation.
     */
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
