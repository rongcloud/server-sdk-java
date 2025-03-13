package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * Group Administrator
 *
 * @author RongCloud
 */
public class GroupManger {
    /**
     * User ID
     */
    private String userId;
    /**
     * User avatar
     */
    private String portraitUri;
    /**
     * User name
     */
    private String name;
    /**
     * Group alias: defaults to the user name if not set
     */
    private String remarkName;
    /**
     * Time when the user became an administrator
     */
    private Long time;
    /**
     * Additional member information
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
