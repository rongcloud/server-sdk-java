package io.rong.models.profile;

/**
 * @author RongCloud
 */
public class CreateEntrustGroupModel extends EntrustGroupModel {

    private String[] userIds;

    public String[] getUserIds() {
        return userIds;
    }

    public CreateEntrustGroupModel setUserIds(String[] userIds) {
        this.userIds = userIds;
        return this;
    }
}
