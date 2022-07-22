package io.rong.models.response;

import io.rong.models.user.BlockPushPeriodModel;
import io.rong.util.GsonUtil;

/**
 * @author jinyuhe
 * @date 2022/07/22
 */
public class BlockPushPeriodResult extends ResponseResult{
    private BlockPushPeriodModel data;

    public BlockPushPeriodResult(Integer code, String msg, BlockPushPeriodModel data) {
        super(code, msg);
        this.data = data;
    }

    public BlockPushPeriodModel getData() {
        return data;
    }

    public void setData(BlockPushPeriodModel data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, BlockPushPeriodResult.class);
    }
}
