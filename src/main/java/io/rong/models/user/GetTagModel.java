package io.rong.models.user;

import io.rong.util.GsonUtil;

/**
 * 批量用户标签信息
 * @author jiangxinjun
 * @date 2019-02-27
 */
public class GetTagModel {

    /**
     *  用户 Id，一次最多支持 50 个用户。（必传）
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
