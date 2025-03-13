package io.rong.models.user;

import io.rong.methods.user.User;
import io.rong.util.GsonUtil;

/**
*
* User Information
* */
public class UserModel {

    /**
     * User ID, with a maximum length of 64 bytes. It is the unique identifier for the user within the App.
     * It must be ensured that the same User ID is not duplicated within the same App. Duplicate User IDs will be treated as the same user. (Required)
     */
    public String id;

    public String[] ids;
    /**
     * User name, with a maximum length of 128 bytes. Used to display the user's name in Push notifications.
     * The user name will be updated within 5 minutes after refreshing. (Optional, refresh if provided, ignore if not)
     */
    public String name;
    /**
     * User avatar URI, with a maximum length of 1024 bytes.
     * Used to display in Push notifications. (Optional, refresh if provided, ignore if not)
     */
    public String portrait;

    private Integer minute;
    /**
     * Blocklist.
     */
    private UserModel[] blacklist;
    /**
     * Allowlist.
     */
    private UserModel[] whitelist;


    public UserModel() {
    }

    public UserModel(String id, String name, String portrait) {
        this.id = id;
        this.name = name;
        this.portrait = portrait;
    }
    public UserModel(String[] ids) {
        this.ids = ids;
    }

    public String getId() {
        return this.id;
    }

    public UserModel setId(String id) {
        this.id = id;
        return this;
    }

    public String[] getIds() {
        return ids;
    }

    public UserModel setIds(String[] ids) {
        this.ids = ids;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public UserModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getPortrait() {
        return this.portrait;
    }

    public UserModel setPortrait(String portrait) {
        this.portrait = portrait;
        return this;
    }

    public Integer getMinute() {
        return this.minute;
    }

    public UserModel setMinute(Integer minute) {
        this.minute = minute;
        return this;
    }

    public UserModel[] getBlacklist() {
        return this.blacklist;
    }

    public UserModel setBlacklist(UserModel[] blacklist) {
        this.blacklist = blacklist;
        return this;
    }

    public UserModel[] getWhitelist() {
        return whitelist;
    }

    public UserModel setWhitelist(UserModel[] whitelist) {
        this.whitelist = whitelist;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, UserModel.class);
    }
}
