package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

public class GroupRemarkModel extends Result {
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toString() {
        return GsonUtil.toJson(this, GroupRemarkModel.class);
    }
}
