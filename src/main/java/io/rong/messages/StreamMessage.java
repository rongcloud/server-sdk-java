package io.rong.messages;

import io.rong.util.GsonUtil;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Stream message (RC:StreamMsg).
 */
public class StreamMessage extends BaseMessage {

    private transient static final String TYPE = "RC:StreamMsg";

    private transient AtomicLong seqCounter = new AtomicLong(0);

    private String content;
    private Long seq;
    private Boolean complete;
    private Integer completeReason;
    private String type;
    private String messageUID;
    private UserInfoJsonExtra user;
    private HashMap<String, String> extra;

    public StreamMessage() {
    }

    public StreamMessage(String content, Boolean complete) {
        this.content = content;
        this.complete = complete;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Builds the JSON content string with auto-incremented seq.
     * Use this method when sending stream messages.
     */
    public String build() {
        this.seq = seqCounter.incrementAndGet();
        return GsonUtil.toJson(this, StreamMessage.class);
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, StreamMessage.class);
    }

    /**
     * Resets the seq counter to 0.
     */
    public void resetSeq() {
        seqCounter.set(0);
    }

    public String getContent() {
        return content;
    }

    public StreamMessage setContent(String content) {
        this.content = content;
        return this;
    }

    public Long getSeq() {
        return seq;
    }

    public StreamMessage setSeq(Long seq) {
        this.seq = seq;
        this.seqCounter.set(seq);
        return this;
    }

    public Boolean getComplete() {
        return complete;
    }

    public StreamMessage setComplete(Boolean complete) {
        this.complete = complete;
        return this;
    }

    public Integer getCompleteReason() {
        return completeReason;
    }

    public StreamMessage setCompleteReason(Integer completeReason) {
        this.completeReason = completeReason;
        return this;
    }

    public String getStreamType() {
        return type;
    }

    public StreamMessage setStreamType(String type) {
        this.type = type;
        return this;
    }

    public String getMessageUID() {
        return messageUID;
    }

    public StreamMessage setMessageUID(String messageUID) {
        this.messageUID = messageUID;
        return this;
    }

    public UserInfoJsonExtra getUser() {
        return user;
    }

    public StreamMessage setUser(UserInfoJsonExtra user) {
        this.user = user;
        return this;
    }

    public HashMap<String, String> getExtra() {
        return extra;
    }

    public StreamMessage setExtra(HashMap<String, String> extra) {
        this.extra = extra;
        return this;
    }
}
