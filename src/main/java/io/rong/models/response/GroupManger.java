package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * 群组管理员
 *
 * @author RongCloud
 */
public class GroupManger {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户头像
     */
    private String portraitUri;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 群昵称：默认未设置时为用户名称
     */
    private String remarkName;
    /**
     * 成为管理员时间
     */
    private Long time;
    /**
     * 成员附加信息
     */
    private String extra;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, GroupManger.class);
    }
}
