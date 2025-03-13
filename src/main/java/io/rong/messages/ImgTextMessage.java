package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * Image-text message.
 *
 */
public class ImgTextMessage extends BaseMessage {
    private String content = "";
    private String extra = "";
    private String title = "";
    private String imageUri = "";
    private String url = "";
    private UserInfo user = null;
    private transient static final String TYPE = "RC:ImgTextMsg";

    public ImgTextMessage(String content, String extra, String title, String imageUri, String url) {
        this.content = content;
        this.extra = extra;
        this.title = title;
        this.imageUri = imageUri;
        this.url = url;
    }

    public ImgTextMessage(String content, String extra, String title, String imageUri, String url, UserInfo user) {
        this.content = content;
        this.extra = extra;
        this.title = title;
        this.imageUri = imageUri;
        this.url = url;
        this.user = user;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Gets the text content of the message.
     *
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content Sets the text content of the message.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Get the extra information (if developers need it, they can parse it on the App side).
     *
     * @return String
     */
    public String getExtra() {
        return extra;
    }

    /**
     * @param extra Set the extra information (if developers need it, they can parse it on the App side).
     *
     *
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * Get the message title.
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title Set the message title.
     *
     *
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the image URL.
     *
     * @return String
     */
    public String getImageUri() {
        return imageUri;
    }


    /**
     * @param imageUri Sets the image URL.
     *
     *
     */
    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    /**
     * Gets the URL for redirection.
     *
     * @return String
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url Sets the URL for redirection.
     *
     *
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the user information carried in the message (The IMKit SDK chat UI prioritizes displaying the user information carried in the message).
     *
     * @return UserInfo
     */
    public UserInfo getUser() {
        return user;
    }

    /**
     * @param user The user information carried in the message (The IMKit SDK chat UI prioritizes displaying the user information carried in the message).
     */
    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ImgTextMessage.class);
    }
}