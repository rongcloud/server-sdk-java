package io.rong.models.response;


import io.rong.util.GsonUtil;


/**
 * @author RongCloud
 */
public class ChatMetadata {

    private Usage usage;

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ChatMetadata.class);
    }
}
