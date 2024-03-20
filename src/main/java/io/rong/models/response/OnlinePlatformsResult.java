package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;
import io.rong.util.CodeUtil.OnlinePlatform;
import java.util.List;
import java.util.ArrayList;


/**
 * 查询指定用户所有在线平台返回结果
 */
public class OnlinePlatformsResult extends Result {

    private List<Integer> list;

    public List<OnlinePlatform> getList() {
        return OnlinePlatform.byValues(this.list);
    }
    @Override
    public String toString() {
        return GsonUtil.toJson(this, OnlinePlatformsResult.class);
    }
}
