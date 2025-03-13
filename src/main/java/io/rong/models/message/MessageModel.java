package io.rong.models.message;

import io.rong.util.GsonUtil;
import io.rong.messages.BaseMessage;


/**
 * Represents the message body for sending messages.
 *
 * @author RongCloud
 */
public class MessageModel {

    private String senderId;
    /**
     * Target ID, which could be a user ID, chat ID, group ID, or discussion group ID (required).
     **/
    private String[] targetId;
    /**
     * Message type (required).
     **/
    private String objectName;
    /**
     * Message content, refer to the RongCloud message type table for examples. If the objectName is a custom message type, this parameter can be customized (required).
     **/
    private BaseMessage content;
    /**
     * Defines the Push notification content. If the objectName is a built-in RongCloud message type, the user will always receive a Push notification. If it's a custom message, pushContent will be the displayed Push content. If not provided, the user will not receive a Push notification (optional).
     */
    private String pushContent;
    /**
     * For iOS platforms, this is attached to the payload when sending Push notifications. For Android clients, the corresponding field name is pushData (optional).
     */
    private String pushData;

    /**
     * Push notification attribute settings. Refer to the pushExt structure for details. pushExt is a JSON structure and requires escaping when sending requests (optional).
     */
    private String pushExt;

    /**
     * Idempotency feature note:
     *
     * The idempotency feature needs to be enabled before use. If needed, submit a ticket to apply. By default, if the msgRandom identifier is repeated within 1 minute, an error code will be returned. After 1 minute, the server will no longer handle idempotency, but the RongCloud SDK will still ensure idempotency.
     */
    private Long msgRandom;

    /**
     * Disables updating the last message in the conversation. When this parameter is set to false, the sent message will appear in the conversation list; when set to true, the message content will not be updated in the conversation list.
     * Note: This parameter only applies to messages stored on the client side.
     */
    private Boolean disableUpdateLastMsg;


    public MessageModel() {
    }

    public MessageModel(String senderId, String[] targetId, String objectName, BaseMessage content,
                        String pushContent, String pushData) {
        this.senderId = senderId;
        this.targetId = targetId;
        this.objectName = objectName;
        this.content = content;
        this.pushContent = pushContent;
        this.pushData = pushData;
        this.pushExt = null;
    }

    public MessageModel(String senderId, String[] targetId, String objectName, BaseMessage content,
                        String pushContent, String pushData, String pushExt) {
        this.senderId = senderId;
        this.targetId = targetId;
        this.objectName = objectName;
        this.content = content;
        this.pushContent = pushContent;
        this.pushData = pushData;
        this.pushExt = pushExt;
    }

    public String[] getTargetId() {
        return this.targetId;
    }

    public MessageModel setTargetId(String[] targetId) {
        this.targetId = targetId;
        return this;
    }

    public String getObjectName() {
        return this.objectName;
    }

    /**
     * This property is no longer in use. The message type is now obtained via the getType method in BaseMessage.
     */
    @Deprecated
    public MessageModel setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public BaseMessage getContent() {
        return this.content;
    }

    public MessageModel setContent(BaseMessage content) {
        this.content = content;
        return this;
    }

    public String getPushContent() {
        return this.pushContent;
    }

    public MessageModel setPushContent(String pushContent) {
        this.pushContent = pushContent;
        return this;
    }

    public String getPushData() {
        return this.pushData;
    }

    public MessageModel setPushData(String pushData) {
        this.pushData = pushData;
        return this;
    }

    public String getPushExt() {
        return this.pushExt;
    }

    public MessageModel setPushExt(String pushExt) {
        this.pushExt = pushExt;
        return this;
    }

    /**
     * Get the PushExt JSON parameter
     *
     * @param pe  The PushExt object to be constructed
     * @return
     */
    public MessageModel setPushExt(PushExt pe) {
        this.pushExt = GsonUtil.toJson(pe, PushExt.class);
        return this;
    }


    public String getSenderId() {
        return this.senderId;
    }

    public MessageModel setSenderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    public Long getMsgRandom() {
        return msgRandom;
    }

    public MessageModel setMsgRandom(Long msgRandom) {
        this.msgRandom = msgRandom;
        return this;
    }

    public Boolean getDisableUpdateLastMsg() {
        return disableUpdateLastMsg;
    }

    public MessageModel setDisableUpdateLastMsg(Boolean disableUpdateLastMsg) {
        this.disableUpdateLastMsg = disableUpdateLastMsg;
        return this;
    }
}
