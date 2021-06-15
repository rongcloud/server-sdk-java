package io.rong.models.user;

import io.rong.util.GsonUtil;

public class BanListModel {

    /**
     * 获取行数，默认为 100，最大支持 200 个。
     */
    private Integer  num = 100;
    /**
     * 查询开始位置，默认为 0。
     */
    private Integer  offset = 0;
    /**
     * 会话类型，目前支持单聊会话 PERSON
     */
    private String  type;

    public Integer getNum() {
        return num;
    }

    public BanListModel setNum(Integer num) {
        this.num = num;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public BanListModel setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public String getType() {
        return type;
    }

    public BanListModel setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, BanListModel.class);
    }
}
