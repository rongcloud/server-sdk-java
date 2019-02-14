package io.rong.messages;

import io.rong.util.GsonUtil;

import java.util.Map;
/**
 * 群组通知消息
 */
public class GroupNotificationMessage extends BaseMessage {
    //操作人用户 Id
    private String operatorUserId;

    //操作名称
    private String operation;

    //群组中各种通知的操作数据
    private Map<String,Object> data;

    //消息内容
    private String message;

    //可以放置任意的数据内容，也可以去掉此属性
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
