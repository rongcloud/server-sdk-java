package io.rong.messages;

import io.rong.util.GsonUtil;

import java.util.Map;
/**
 * Group Notification Message
 */
public class GroupNotificationMessage extends BaseMessage {
    // The user ID of the operator
    private String operatorUserId;

    // The name of the operation
    private String operation;

    // The operation data for various notifications in the group
    private Map<String, Object> data;

    // The content of the message
    private String message;

    // Can store any data content, or this attribute can be removed
    private String extra;

    private transient static final String TYPE = "RC:GrpNtf";

    public GroupNotificationMessage(String operatorUserId, String operation, Map<String, Object> data, String message, String extra) {
        this.operatorUserId = operatorUserId;
        this.operation = operation;
        this.data = data;
        this.message = message;
        this.extra = extra;
    }

    public String getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(String operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, GroupNotificationMessage.class);
    }
}
