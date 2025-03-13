package io.rong.models.message;

public class RecallMessage {
    /**
     * Recall message body
     * Sender ID
     */
    public String senderId;
    /**
     * Target ID
     */
    public String targetId;
    /**
     * Unique identifier of the message. Each SDK returns a uId after successfully sending a message.
     */
    public String uId;
    /**
     * Timestamp of the message sent. Each SDK returns a sentTime after successfully sending a message (optional).
     */
    public String sentTime;

    /**
     * Indicates whether the sender is an admin. Default is 0. When set to 1, IMKit will display a gray bar notification as "Admin recalled a message" (optional).
     */
    public Integer isAdmin;

    /**
     * Default is 0, which means the message will be deleted and replaced with a gray bar recall notification. When set to 1, the message will be deleted without displaying a gray bar notification (optional).
     */
    public Integer isDelete;

    /**
     * Extended information, which can contain any data content (optional).
     */
    public String extra;

    /**
     * Indicates whether the message is silent. Default is false. When set to true, offline users will not receive a notification reminder (optional). Not supported in global data center.
     */
    public Boolean disablePush;

    /**
     * Ultra group channel ID, only applicable for recalling ultra group messages. Usage requirements:
     * If a channel ID was specified when sending the message, it must be specified when recalling; otherwise, the recall will fail.
     * If no channel ID was specified when sending the message, it must not be specified when recalling; otherwise, the recall will fail.
     * When sending ultra group messages from the client, the channel ID field is named channelId.
     */
    private  String busChannel;

    /**
     * Disable updating the last message in the conversation. When this parameter is set to false, the sent message will appear in the conversation list; when set to true, the message content will not be updated in the conversation list.
     * Note: This parameter only affects messages stored on the client side.
     */
    private Boolean disableUpdateLastMsg;

    public RecallMessage() {
    }

    /**
     * @param senderId         String	The user ID of the message sender. (Required)
     * @param conversationType Int	The conversation type: 1 for one-to-one chat, 2 for discussion group, and 3 for group chat. (Required)
     * @param targetId         String	The target ID, which could be a user ID, discussion group ID, or group ID depending on the ConversationType. (Required)
     * @param uId              String	The unique identifier of the message, which can be obtained through the server-side real-time message routing, corresponding to the name msgUID. (Required)
     * @param sentTime
     */
    public RecallMessage(String senderId, String conversationType, String targetId,
                         String uId, String sentTime) {
        this.senderId = senderId;
        this.targetId = targetId;
        this.uId = uId;
        this.sentTime = sentTime;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public RecallMessage setSenderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public RecallMessage setTargetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public String getUId() {
        return uId;
    }

    public RecallMessage setuId(String uId) {
        this.uId = uId;
        return this;
    }

    public String getSentTime() {
        return this.sentTime;
    }

    public RecallMessage setSentTime(String sentTime) {
        this.sentTime = sentTime;
        return this;
    }

    public Boolean getDisablePush() {
        return disablePush;
    }

    public RecallMessage setDisablePush(Boolean disablePush) {
        this.disablePush = disablePush;
        return this;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public RecallMessage setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public RecallMessage setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public String getExtra() {
        return extra;
    }

    public RecallMessage setExtra(String extra) {
        this.extra = extra;
        return this;
    }

    public String getBusChannel() {
        return busChannel;
    }

    public RecallMessage setBusChannel(String busChannel) {
        this.busChannel = busChannel;
        return this;
    }


    public Boolean getDisableUpdateLastMsg() {
        return disableUpdateLastMsg;
    }

    public RecallMessage setDisableUpdateLastMsg(Boolean disableUpdateLastMsg) {
        this.disableUpdateLastMsg = disableUpdateLastMsg;
        return this;
    }
}
