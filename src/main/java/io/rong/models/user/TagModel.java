package io.rong.models.user;

import io.rong.util.GsonUtil;

/**
 * User tag information
 *
 */
public class TagModel {

    /**
     * User ID. (Required)
     */
    private String userId;

    /**
     * User tags. A user can add up to 20 tags, each tag cannot exceed 40 bytes, and tags cannot contain special characters. (Required)
     */
    private String[] tags;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, TagModel.class);
    }
}