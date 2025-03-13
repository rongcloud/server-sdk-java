package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 * High-quality voice message
 */
public class HQVoiceMessage extends BaseMessage {
    private String localPath = "";
    private String remoteUrl = "";
    private String extra = "";
    private Long duration = 0L;
    private UserInfo user = null;
    private transient static final String TYPE = "RC:HQVCMsg";

    public HQVoiceMessage(String localPath, String remoteUrl, String extra, Long duration) {
        this.localPath = localPath;
        this.remoteUrl = remoteUrl;
        this.extra = extra;
        this.duration = duration;
    }

    public HQVoiceMessage(String localPath, String remoteUrl, String extra, Long duration, UserInfo user) {
        this.localPath = localPath;
        this.remoteUrl = remoteUrl;
        this.extra = extra;
        this.duration = duration;
        this.user = user;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Gets the local path of the media content encoded in AAC format.
     *
     * @return String
     */
    public String getLocalPath() {
        return localPath;
    }

    /**
     * Sets the local path of the media content encoded in AAC format.
     *
     */
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }


    /**
     * Gets the remote URL of the media content after it is uploaded to the server. The SDK will assign a value to this property after a successful upload in IMKit.
     *
     * @return String
     */
    public String getRemoteUrl() {
        return remoteUrl;
    }

    /**
     * Sets the remote URL of the media content after it is uploaded to the server. The SDK will assign a value to this property after a successful upload in IMKit.
     *
     */
    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    /**
     * Gets the extra information (if needed, developers can parse it on the App side).
     *
     * @return String
     */
    public String getExtra() {
        return extra;
    }

    /**
     * Sets the extra information (if needed, developers can parse it on the App side).
     *
     *
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * Gets the duration.
     *
     * @return Long
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * Sets the duration.
     *
     *
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * Retrieves the user information carried in the message (the chat UI in IMKit SDK prioritizes displaying the user information carried in the message).
     *
     * @return UserInfo
     */
    public UserInfo getUser() {
        return user;
    }

    /**
     * @param user The user information carried in the message (the chat UI in IMKit SDK prioritizes displaying the user information carried in the message).
     */
    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, HQVoiceMessage.class);
    }
}
