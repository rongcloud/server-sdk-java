package io.rong.models.push;

import io.rong.util.GsonUtil;

/**
 * 推送条件
 */
public class Audience {

    /**
     * 用户标签，每次发送时最多发送 20 个标签，标签之间为 AND 的关系，is_to_all 为 true 时参数无效。（非必传）
     */
    protected String[] tag;

    /**
     * 用户标签，每次发送时最多发送 20 个标签，标签之间为 OR 的关系，is_to_all 为 true 时参数无效，tag_or 同 tag
     * 参数可以同时存在。（非必传）
     */
    protected String[] tag_or;

    /**
     * 用户 Id，每次发送时最多发送 1000 个用户，如果 tag 和 userid 两个条件同时存在时，则以 userid 为准，如果 userid
     * 有值时，则 platform 参数无效，is_to_all 为 true 时参数无效。（非必传）
     */
    protected String[] userid;

    /**
     * 是否全部推送，false 表示按 tag 、tag_or 或 userid 条件推送，true 表示向所有用户推送，tag、tag_or 和 userid
     * 条件无效。（必传）
     */
    protected Boolean is_to_all;

    /**
     * 应用包名，is_to_all 为 true 时，此参数无效。与 tag、tag_or 同时存在时为 And 的关系，向同时满足条件的用户推送。与
     * userid 条件同时存在时，以 userid 为准进行推送。（非必传）
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