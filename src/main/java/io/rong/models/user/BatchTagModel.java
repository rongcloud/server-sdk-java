package io.rong.models.user;

import io.rong.util.GsonUtil;

/**
 * 批量用户标签信息
 *
 */
public class BatchTagModel {

    /**
     *  用户 Id，一次最多支持 1000 个用户。（必传）
     */
    private String[] userIds;

    /**
     * 用户标签，一个用户最多添加 20 个标签，每个 tag 最大不能超过 40 个字节，标签中不能包含特殊字符。（必传）
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