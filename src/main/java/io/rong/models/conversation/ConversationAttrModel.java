package io.rong.models.conversation;

import java.util.List;

/**
 * @author RongCloud
 */
public class ConversationAttrModel {

    private String targetId;

    private Integer conversationType;

    private List<ConversationTagModel> tags;

    private ConversationTopModel top;

    private ConversationMuteModel mute;


    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public Integer getConversationType() {
        return conversationType;
    }

    public void setConversationType(Integer conversationType) {
        this.conversationType = conversationType;
    }

    public List<ConversationTagModel> getTags() {
        return tags;
    }

    public void setTags(List<ConversationTagModel> tags) {
        this.tags = tags;
    }

    public ConversationTopModel getTop() {
        return top;
    }

    public void setTop(ConversationTopModel top) {
        this.top = top;
    }

    public ConversationMuteModel getMute() {
        return mute;
    }

    public void setMute(ConversationMuteModel mute) {
        this.mute = mute;
    }
}
