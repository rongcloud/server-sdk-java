package io.rong.models.response;

import io.rong.util.GsonUtil;

public class NotdisturbStatusResult extends ResponseResult{
    String groupId;
    String busChannel;
    int unpushLevel;

    public NotdisturbStatusResult(Integer code, String msg, String groupId, String busChannel, int unpushLevel) {
        super(code, msg);
        this.groupId = groupId;
        this.busChannel = busChannel;
        this.unpushLevel = unpushLevel;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getBusChannel() {
        return busChannel;
    }

    public void setBusChannel(String busChannel) {
        this.busChannel = busChannel;
    }

    public int getUnpushLevel() {
        return unpushLevel;
    }

    public void setUnpushLevel(int unpushLevel) {
        this.unpushLevel = unpushLevel;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, NotdisturbStatusResult.class);
    }
}
