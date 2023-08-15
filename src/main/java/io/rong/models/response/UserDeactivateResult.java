package io.rong.models.response;

import io.rong.models.Result;

import java.util.List;

public class UserDeactivateResult extends Result {
    private List<String> users;

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserAbandonResult{" +
                "code=" + code +
                ", errorMessage='" + errorMessage + '\'' +
                ", requestId='" + requestId + '\'' +
                ", users=" + users +
                '}';
    }
}
