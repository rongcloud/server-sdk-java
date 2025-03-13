package io.rong.models.response;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * @author RongCloud
 */
public class PagingQueryBlacklistResult extends ResponseResult {
    /**
     * List of users in the blocklist
     */
    private List<String> users;

    /**
     * Pagination token. If present, indicates there are more results to fetch in the next request.
     */
    private String next;

    public PagingQueryBlacklistResult(Integer code, String msg) {
        super(code, msg);
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PagingQueryBlacklistResult.class);
    }

}
