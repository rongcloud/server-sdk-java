package io.rong.models.response;


public class HistoryMessage{

    /**
     * Conversation id
     */
    private String targetId;
    /**
     * Message sender ID
     */
    private String fromUserId;
    /**
     * ultra group busChannel
     */
    private String busChannel;
    /**
     * message ID
     */
    private String messageUID;
    /**
     * Message timestamp
     */
    private Long msgTime;
    /**
     * Message type
     */
    private String objectName;
    /**
     * Message content
     */
    private String content;
    /**
     * Whether the message has been set to extensible. true means extensible. False means that it is not extensible.
     */
    private Boolean expansion;
    /**
     * Content of message extension, JSON structure
     */
    private String extraContent;


    public String getTargetId() {
        return targetId;
    }

    public HistoryMessage setTargetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public HistoryMessage setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }

    public String getMessageUID() {
        return messageUID;
    }

    public HistoryMessage setMessageUID(String messageUID) {
        this.messageUID = messageUID;
        return this;
    }

    public Long getMsgTime() {
        return msgTime;
    }

    public HistoryMessage setMsgTime(Long msgTime) {
        this.msgTime = msgTime;
        return this;
    }

    public String getObjectName() {
        return objectName;
    }

    public HistoryMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public String getContent() {
        return content;
    }

    public HistoryMessage setContent(String content) {
        this.content = content;
        return this;
    }

    public Boolean getExpansion() {
        return expansion;
    }

    public HistoryMessage setExpansion(Boolean expansion) {
        this.expansion = expansion;
        return this;
    }

    public String getBusChannel() {
        return busChannel;
    }

    public HistoryMessage setBusChannel(String busChannel) {
        this.busChannel = busChannel;
        return this;
    }

    public String getExtraContent() {
        return extraContent;
    }

    public HistoryMessage setExtraContent(String extraContent) {
        this.extraContent = extraContent;
        return this;
    }
}
