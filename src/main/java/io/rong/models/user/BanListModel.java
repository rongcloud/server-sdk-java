package io.rong.models.user;

import io.rong.util.GsonUtil;

public class BanListModel {

    /**
     * Number of rows to fetch, default is 100, maximum supported is 200.
     */
    private Integer num = 100;
    /**
     * Starting position for query, default is 0.
     */
    private Integer offset = 0;
    /**
     * Conversation type, currently supports one-to-one chat PERSON.
     */
    private String type;

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
