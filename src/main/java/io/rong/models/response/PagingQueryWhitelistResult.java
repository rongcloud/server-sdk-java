package io.rong.models.response;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * @author RongCloud
 */
public class PagingQueryWhitelistResult extends ResponseResult {
    /**
     * 白名单用户列表
     */
    private List<String> users;

    /**
     * 分页  token, 有则表示还有下一页，下一次请求携带
     */
    private String next;

    public PagingQueryWhitelistResult(Integer code, String msg) {
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
        return GsonUtil.toJson(this, PagingQueryWhitelistResult.class);
    }

}
