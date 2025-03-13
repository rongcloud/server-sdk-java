package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 * Represents an image message.
 */
public class ImgMessage extends BaseMessage {
    private String content = "";
    private String extra = "";
    private String imageUri = "";
    private UserInfo user = null;
    private transient static final String TYPE = "RC:ImgMsg";

    public ImgMessage(String content, String extra, String imageUri) {
        this.content = content;
        this.extra = extra;
        this.imageUri = imageUri;
    }

    public ImgMessage(String content, String extra, String imageUri, UserInfo user) {
        this.content = content;
        this.extra = extra;
        this.imageUri = imageUri;
        this.user = user;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Gets the thumbnail of the image, formatted as JPG, with a size not exceeding 30k.
     * Note: After Base64 encoding, all \r\n, \r, and \n should be replaced with empty strings.
     *
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the thumbnail of the image, formatted as JPG, with a size not exceeding 30k.
     * Note: After Base64 encoding, all \r\n, \r, and \n should be replaced with empty strings.
     *
     *
     */
    public void setContent(String content) {
        this.content = content;
    }


    /**
     * Retrieves the extra information (if developers need it, they can parse it on the App side).
     *
     * @return String
     */
    public String getExtra() {
        return extra;
    }

    /**
     * Sets the extra information (if developers need it, they can parse it on the App side).
     *
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * Retrieves the image URL.
     *
     * @return String
     */
    public String getImageUri() {
        return imageUri;
    }

    /**
     * Sets the image URL.
     *
     */
    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    /**
     * Retrieves the user information carried in the message (the IMKit SDK chat UI prioritizes displaying the user information carried in the message).
     *
     * @return UserInfo
     */
    public UserInfo getUser() {
        return user;
    }

    /**
     * Sets the user information carried in the message (the IMKit SDK chat UI prioritizes displaying the user information carried in the message).
     *
     * @param user The user information to set.
     */
    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ImgMessage.class);
    }
}