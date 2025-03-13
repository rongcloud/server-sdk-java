package io.rong.models.user;

import io.rong.util.GsonUtil;

/**
 * Batch user tag information
 *
 */
public class BatchTagModel {

    /**
     * User IDs, up to 1000 users supported at once. (Required)
     */
    private String[] userIds;

    /**
     * User tags, each user can have up to 20 tags, each tag cannot exceed 40 bytes, and tags cannot contain special characters. (Required)
     */
    private String[] tags;

    public String[] getUserIds() {
        return userIds;
    }

    public void setUserIds(String[] userIds) {
        this.userIds = userIds;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, BatchTagModel.class);
    }
}