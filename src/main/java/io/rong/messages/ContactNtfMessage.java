package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * Add contact notification message.
 *
 */
public class ContactNtfMessage extends BaseMessage {
    private String operation = "";
    private String extra = "";
    private String sourceUserId = "";
    private String targetUserId = "";
    private String message = "";
    private transient static final String TYPE = "RC:ContactNtf";

    public ContactNtfMessage(String operation, String extra, String sourceUserId, String targetUserId, String message) {
        this.operation = operation;
        this.extra = extra;
        this.sourceUserId = sourceUserId;
        this.targetUserId = targetUserId;
        this.message = message;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Get the operation name.
     *
     * @return String
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation Set the operation name.
     *
     *
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Get the extra information (if needed, developers can parse it on the App side).
     */

    /**
     * @return String
     */
    public String getExtra() {
        return extra;
    }

    /**
     * @param extra Set as additional information (can be parsed by the developer on the App side if needed).
     *
     *
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * Get the UserId of the requester or responder.
     *
     * @return String
     */
    public String getSourceUserId() {
        return sourceUserId;
    }

    /**
     * @param sourceUserId Set the UserId of the requester or responder.
     *
     *
     */
    public void setSourceUserId(String sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    /**
     * Get the UserId of the requestee or respondee.
     *
     * @return String
     */
    public String getTargetUserId() {
        return targetUserId;
    }

    /**
     * @param targetUserId Set the UserId of the requestee or respondee.
     *
     *
     */
    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }


    /**
     * Retrieves the request or response message.
     *
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the request or response message.
     *
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ContactNtfMessage.class);
    }
}