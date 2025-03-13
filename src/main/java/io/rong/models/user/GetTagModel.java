package io.rong.models.user;

import io.rong.util.GsonUtil;

/**
 * Batch user tag information
 *
 */
public class GetTagModel {

    /**
     * User IDs, supports up to 50 users at a time. (Required)
     */
    private String[] userIds;

    public String[] getUserIds() {
        return userIds;
    }

    public void setUserIds(String[] userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, GetTagModel.class);
    }
}