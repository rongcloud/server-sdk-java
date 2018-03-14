package io.rong.models.user;

import io.rong.methods.user.User;
import io.rong.util.GsonUtil;

/**
*
* 用户信息
* */
public class UserModel {

    /**
     * 用户 Id，最大长度 64 字节.是用户在 App 中的唯一标识码，
     * 必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。（必传）
     */
    public String id;
    /**
     * 用户名称，最大长度 128 字节。用来在 Push 推送时，显示用户的名称，
     * 刷新用户名称后 5 分钟内生效。（可选，提供即刷新，不提供忽略）
     */
    public String name;
    /**
     * 用户头像 URI，最大长度 1024 字节。
     * 用来在 Push 推送时显示。（可选，提供即刷新，不提供忽略)
     */
    public String portrait;

    private Integer minute;
    /**
     * 黑名单列表。
     */
    private UserModel[] blacklist;


    public UserModel() {
    }

    public UserModel(String id, String name, String portrait) {
        this.id = id;
        this.name = name;
        this.portrait = portrait;
    }

    public String getId() {
        return this.id;
    }

    public UserModel setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return GsonUtil.toJson(this, UserModel.class);
    }
}
