package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * broadcast 返回结果
 * 
 * @author jiangxinjun
 * @date 2019-02-27
 */
public class BroadcastResult extends Result {
    
    /**
     * 广播消息唯一标识。
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
        return GsonUtil.toJson(this, BroadcastResult.class);
    }
}
