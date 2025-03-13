package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

import java.util.List;

/**
 * Response result for getuserRemark
 */
public class GetUserRemarksResult extends Result {

    private int total;
    List<UserRemark> users;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<UserRemark> getUsers() {
        return users;
    }

    public void setUsers(List<UserRemark> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, GetUserRemarksResult.class);
    }

    private class UserRemark {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        private String remark;
    }
}