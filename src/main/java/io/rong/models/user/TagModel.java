package io.rong.models.user;

import io.rong.util.GsonUtil;

/**
 * 用户标签信息
 *
 */
public class TagModel {

    /**
     * 用户 Id。（必传）
     */
    private String userId;

    /**
     * 用户标签，一个用户最多添加 20 个标签，每个 tag 最大不能超过 40 个字节，标签中不能包含特殊字符。（必传）
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