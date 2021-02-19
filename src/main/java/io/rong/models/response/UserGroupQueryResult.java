package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.user.UserModel;
import io.rong.util.GsonUtil;
import io.rong.models.group.GroupModel;

public class UserGroupQueryResult extends Result {

    private String reqBody;

    private GroupModel[] groups;

    public UserGroupQueryResult(Integer code, String msg, GroupModel[] groups) {
        super(code, msg);
        this.groups = groups;
    }

    public UserGroupQueryResult(Integer code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public UserGroupQueryResult(GroupModel[] groups) {
        this.groups = groups;
    }

    public GroupModel[] getGroups() {
        return this.groups;
    }

    public void setGroups(GroupModel[] groups) {
        this.groups = groups;
    }

    public String getReqBody() {
        return reqBody;
    }

    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, UserGroupQueryResult.class);
    }

}
