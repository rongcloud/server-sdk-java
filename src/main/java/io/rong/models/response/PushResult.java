package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * push 返回结果
 * 
 * @author jiangxinjun
 * @date 2019-02-27
 */
public class PushResult extends Result {
    
    /**
     * 推送唯一标识。
     */
    private String id;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return GsonUtil.toJson(this, PushResult.class);
    }
}
