package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * getTag 返回结果
 *
 */
public class GetTagResult extends Result {

    /**
     * 用户所有的标签数组。
     */
    private String[] tags;

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, GetTagResult.class);
    }
}