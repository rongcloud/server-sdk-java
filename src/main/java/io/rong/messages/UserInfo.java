package io.rong.messages;

/**
 * 消息中携带的用户信息
 */
public class UserInfo {
    private String id;
    private String name;
    private String portrait;
    private String extra;

    public UserInfo() {
    }
    public UserInfo(String id, String name,String portrait,String extra) {
        this.id = id;
        this.name = name;
        this.portrait = portrait;
        this.extra = extra;
    }
    /**
     * 获取 发送人用户 Id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 发送人用户 Id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取 发送用户显示名称
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 发送用户显示名称
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 发送用户显示头象
     * @return
     */
    public String getPortrait() {
        return portrait;
    }

    /**
     * 设置 发送用户显示头象
     * @param portrait
     */
    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }


    /**
     * 获取 扩展信息
     * @return
     */
    public String getExtra() {
        return extra;
    }

    /**
     * 设置 扩展信息
     * @param extra
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }
}
