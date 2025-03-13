package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 * Text message.
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
     * Gets the message content.
     *
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the message content.
     *
     * @param content The message content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the extra information (can be parsed by the App if needed).
     *
     * @return String
     */
    public String getExtra() {
        return extra;
    }

    /**
     * Sets the extra information.
     *
     * @param extra The extra information to set.
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * Gets the user information.
     *
     * @return UserInfo
     */
    public UserInfo getUser() {
        return user;
    }

    /**
     * Sets the user information.
     *
     * @param user The user information to set.
     */
    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, TxtMessage.class);
    }
}