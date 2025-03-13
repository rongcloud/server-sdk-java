package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

public class ChatroomWhitelistMsgResult extends Result {
    private String[] objectNames;

    /**
     * Constructs a new ChatroomWhitelistMsgResult with the specified code, message, and object names.
     *
     * @param code        The result code.
     * @param msg         The result message.
     * @param objectNames The list of object names in the chatroom allowlist.
     */
    public ChatroomWhitelistMsgResult(Integer code, String msg, String[] objectNames) {
        super(code, msg);
        this.objectNames = objectNames;
    }

    /**
     * Constructs a new ChatroomWhitelistMsgResult with the specified object names.
     *
     * @param objectNames The list of object names in the chatroom allowlist.
     */
    public ChatroomWhitelistMsgResult(String[] objectNames) {
        this.objectNames = objectNames;
    }

    /**
     * Returns the list of object names in the chatroom allowlist.
     *
     * @return The list of object names.
     */
    public String[] getObjectNames() {
        return this.objectNames;
    }

    /**
     * Sets the list of object names in the chatroom allowlist.
     *
     * @param objectNames The list of object names to set.
     */
    public void setObjectNames(String[] objectNames) {
        this.objectNames = objectNames;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ChatroomWhitelistMsgResult.class);
    }
}
