package io.rong.models.user;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

import java.util.List;

public class UserIdListModel extends Result {
    private List<String> members;

    public UserIdListModel(Integer code, String errorMessage, List<String> members) {
        super(code, errorMessage);
        this.members = members;
    }

    public UserIdListModel(List<String> members) {
        this.members = members;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, UserIdListModel.class);
    }
}
