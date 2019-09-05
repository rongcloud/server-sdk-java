package io.rong.messages;

import io.rong.util.GsonUtil;

public class RcCmdMessage extends BaseMessage {
    private transient static final String TYPE = "RC:RcCmd";
    private String conversationType = "6";
    private String messageUId;
    private int isAdmin = 0;
    private int isDelete = 0;


    public RcCmdMessage(String messageUId) {
        this.messageUId = messageUId;
    }

    public RcCmdMessage(String messageUId, int isAdmin, int isDelete) {
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

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
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
