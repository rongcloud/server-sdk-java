package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 * 文本消息。
 */
public class TxtMessage extends BaseMessage {

    private String content = "";
    private String extra = "";
    private UserInfo user = null;
    private transient static final String TYPE = "RC:TxtMsg";

    public TxtMessage(String content, String extra) {
        this.content = content;
        this.extra = extra;
    }

    public TxtMessage(String content, String extra, UserInfo user) {
        this.content = content;
        this.extra = extra;
        this.user = user;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * 获取消息内容。
     *
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content 设置消息内容。
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取附加信息(如果开发者自己需要，可以自己在 App 端进行解析)。
     *
     * @return String
     */
    public String getExtra() {
        return extra;
    }

    /**
     * @param extra 设置附加信息(如果开发者自己需要，可以自己在 App 端进行解析)。
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * 获取消息中携带的用户信息(IMKit SDK 会话界面中优先显示消息中携带的用户信息)。
     *
     * @return UserInfo
     */
    public UserInfo getUser() {
        return user;
    }

    /**
     * @param user 消息中携带的用户信息(IMKit SDK 会话界面中优先显示消息中携带的用户信息)。
     */
    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, TxtMessage.class);
    }
}