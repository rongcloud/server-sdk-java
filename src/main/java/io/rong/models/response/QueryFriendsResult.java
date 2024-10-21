package io.rong.models.response;


import io.rong.util.GsonUtil;

import java.util.List;

/**
 * @author RongCloud
 */
public class QueryFriendsResult extends PageResult {


    private List<FriendProfile> friendList;

    public QueryFriendsResult(Integer code, String msg) {
        super(code, msg);
    }

    public List<FriendProfile> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<FriendProfile> friendList) {
        this.friendList = friendList;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, QueryFriendsResult.class);
    }
}
