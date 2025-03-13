package io.rong.models.push;

import io.rong.util.GsonUtil;

/**
 * Push conditions
 */
public class Audience {

    /**
     * User tags. Up to 20 tags can be sent at a time, and the relationship between tags is AND. This parameter is invalid when is_to_all is true. (Optional)
     */
    protected String[] tag;

    /**
     * User tags. Up to 20 tags can be sent at a time, and the relationship between tags is OR. This parameter is invalid when is_to_all is true. tag_or can coexist with tag. (Optional)
     */
    protected String[] tag_or;

    /**
     * User IDs. Up to 1000 users can be sent at a time. If both tag and userid conditions exist, userid takes precedence. If userid is provided, the platform parameter is invalid. This parameter is invalid when is_to_all is true. (Optional)
     */
    protected String[] userid;

    /**
     * Whether to push to all users. false means pushing based on tag, tag_or, or userid conditions. true means pushing to all users, and tag, tag_or, and userid conditions are invalid. (Required)
     */
    protected Boolean is_to_all;

    /**
     * Application package name. This parameter is invalid when is_to_all is true. When coexisting with tag or tag_or, it forms an AND relationship, pushing to users who meet all conditions. When coexisting with userid, userid takes precedence. (Optional)
     */
    protected String packageName;

    public String[] getTag() {
        return tag;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }

    public String[] getTag_or() {
        return tag_or;
    }

    public void setTag_or(String[] tag_or) {
        this.tag_or = tag_or;
    }

    public String[] getUserid() {
        return userid;
    }

    public void setUserid(String[] userid) {
        this.userid = userid;
    }

    public Boolean getIs_to_all() {
        return is_to_all;
    }

    public void setIs_to_all(Boolean is_to_all) {
        this.is_to_all = is_to_all;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Audience() {
        tag = new String[]{};
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, Audience.class);
    }
}