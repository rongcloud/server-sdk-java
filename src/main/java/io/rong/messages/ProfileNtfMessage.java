package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 * Profile notification message. This type of message does not have a push notification.
 */
public class ProfileNtfMessage extends BaseMessage {
    private String operation = "";
    private String data = "";
    private String extra = "";
    private transient static final String TYPE = "RC:ProfileNtf";

    public ProfileNtfMessage(String operation, String data, String extra) {
        this.operation = operation;
        this.data = data;
        this.extra = extra;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Gets the operation for the profile notification, which can be customized.
     *
     * @return String
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation Sets the operation for the profile notification, which can be customized.
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Gets the data for the operation.
     *
     * @return String
     */
    public String getData() {
        return data;
    }


    /**
     * @param data The data to be set for the operation.
     *
     *
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Retrieves the extra content (if needed, developers can parse it on the App side).
     *
     * @return String
     */
    public String getExtra() {
        return extra;
    }

    /**
     * @param extra Sets the extra content (if needed, developers can parse it on the App side).
     *
     *
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ProfileNtfMessage.class);
    }
}