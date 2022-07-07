package io.rong.models.user;

import io.rong.models.Result;
import io.rong.models.ultragroup.UltraGroupBanModel;
import io.rong.util.GsonUtil;

import java.util.List;

public class UserIdListModel extends Result {
    private List<String> users;

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, UserIdListModel.class);
    }
}
