package io.rong.messages;

import io.rong.util.GsonUtil;

public class RcCmdMessage extends BaseMessage {
    private transient static final String TYPE = "RC:RcCmd";
    private String conversationType = "6";
    private String messageUId;
    private boolean isAdmin = false;
    private boolean isDelete = false;


    public RcCmdMessage(String messageUId) {
        this.messageUId = messageUId;
    }

    public RcCmdMessage(String messageUId, boolean isAdmin, boolean isDelete) {
        this.messageUId = messageUId;
        this.isAdmin = isAdmin;
        this.isDelete = isDelete;
    }

    public String getConversationType() {
        return conversationType;
    }

    public void setConversationType(String conversationType) {
        this.conversationType = conversationType;
    }

    public String getMessageUId() {
        return messageUId;
    }

    public void setMessageUId(String messageUId) {
        this.messageUId = messageUId;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, RcCmdMessage.class);
    }
}
