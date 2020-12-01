package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 * 高清语音消息
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
     * 获取采用 AAC 格式进行编码录制的媒体内容本地路径。
     *
     * @return String
     */
    public String getLocalPath() {
        return localPath;
    }

    /**
     * @param localPath 设置采用 AAC 格式进行编码录制的媒体内容本地路径。
     *
     */
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }


    /**
     * 获取媒体内容上传服务器后的网络地址，IMKit 中上传成功后 SDK 会为该属性赋值。
     *
     * @return String
     */
    public String getRemoteUrl() {
        return remoteUrl;
    }

    /**
     * @param remoteUrl 设置媒体内容上传服务器后的网络地址，IMKit 中上传成功后 SDK 会为该属性赋值。
     *
     */
    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    /**
     * 获取为附加信息(如果开发者自己需要，可以自己在 App 端进行解析)。
     *
     * @return String
     */
    public String getExtra() {
        return extra;
    }

    /**
     * @param extra 设置为附加信息(如果开发者自己需要，可以自己在 App 端进行解析)。
     *
     *
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * 获取持续时间。
     *
     * @return Long
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * @param duration 设置持续时间。
     *
     *
     */
    public void setDuration(Long duration) {
        this.duration = duration;
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
        return GsonUtil.toJson(this, HQVoiceMessage.class);
    }
}
