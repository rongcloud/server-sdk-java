package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * Voice message.
 *
 */
public class VoiceMessage extends BaseMessage {
    private String content = "";
    private String extra = "";
    private Long duration = 0L;
    private UserInfo user = null;
    private transient static final String TYPE = "RC:VcMsg";

    public VoiceMessage(String content, String extra, Long duration) {
        this.content = content;
        this.extra = extra;
        this.duration = duration;
    }

    public VoiceMessage(String content, String extra, Long duration, UserInfo user) {
        this.content = content;
        this.extra = extra;
        this.duration = duration;
        this.user = user;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Gets the voice content, which is in AMR format. After Base64 encoding, all \r\n, \r, and \n should be replaced with empty strings. The size should not exceed 60k, and the duration indicates the length of the voice, with a maximum of 60 seconds.
     *
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content Sets the voice content, which is in AMR format. After Base64 encoding, all \r\n, \r, and \n should be replaced with empty strings. The size should not exceed 60k, and the duration indicates the length of the voice, with a maximum of 60 seconds.
     *
     *
     */
    public void setContent(String content) {
        this.content = content;
    }


    /**
     * Retrieves the extra information (can be parsed by the developer on the App side if needed).
     *
     * @return String
     */
    public String getExtra() {
        return extra;
    }

    /**
     * Sets the extra information (can be parsed by the developer on the App side if needed).
     *
     * @param extra The extra information to set.
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * Retrieves the duration.
     *
     * @return Long
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * Sets the duration.
     *
     * @param duration The duration to set.
     */
    public void setDuration(Long duration) {
        this.duration = duration;
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
        return GsonUtil.toJson(this, VoiceMessage.class);
    }
}