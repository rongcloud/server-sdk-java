package io.rong.models.response;


import io.rong.util.GsonUtil;

import java.util.List;

/**
 * @author RongCloud
 */
public class CheckFriendsResult extends PageResult {


    private List<CheckFriendResult> results;

    public CheckFriendsResult(Integer code, String msg) {
        super(code, msg);
    }

    public List<CheckFriendResult> getResults() {
        return results;
    }

    public void setResults(List<CheckFriendResult> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, CheckFriendsResult.class);
    }
}
