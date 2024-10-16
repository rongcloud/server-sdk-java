package io.rong.models.response;


import io.rong.util.GsonUtil;

import java.util.List;

/**
 * @author RongCloud
 */
public class QueryUserProfilesResp extends ResponseResult {

    private List<UserProfile> userList;

    public QueryUserProfilesResp(Integer code, String msg) {
        super(code, msg);
    }

    public List<UserProfile> getUserList() {
        return userList;
    }

    public void setUserList(List<UserProfile> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, QueryUserProfilesResp.class);
    }
}
