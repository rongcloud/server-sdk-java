package io.rong.models;

public class User {
    /**
     * userId:用户 Id，最大长度 64 字节.是用户在 App 中的唯一标识码，
     * 必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。
     */
    public String id;
    /**
     * 用户名称，最大长度 128 字节。用来在 Push 推送时，显示用户的名称，
     * 刷新用户名称后 5 分钟内生效。（可选，提供即刷新，不提供忽略）
     */
    public String name;
    /**
     * portraitUri:用户头像 URI，最大长度 1024 字节。
     * 用来在 Push 推送时显示。（可选，提供即刷新，不提供忽略)
     */
    public String portraitUri;

    public User(String id, String name,String portraitUri) {
        this.id = id;
        this.name = name;
        this.portraitUri = portraitUri;
    }
    /**
     * 设置id
     *
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取id
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortraitUri() {
        return this.portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }
}
