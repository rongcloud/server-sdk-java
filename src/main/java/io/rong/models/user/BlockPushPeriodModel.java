package io.rong.models.user;

import io.rong.util.GsonUtil;

/**
*
* 用户免打扰
* */
public class BlockPushPeriodModel {

    private String id;
    private String startTime;
    private Integer period;
    private Integer level = 1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, BlockPushPeriodModel.class);
    }
}
