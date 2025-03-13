package io.rong.models.message;

import java.util.Map;

/**
 * Template message body
 * @author hc
 */
public class TemplateMessage {
    /**
     * Sender ID
     * */
    private String senderId;
    /**
     * Message type
     * */
    private String objectName;
    /**
     * Message content, where the template is defined and placeholders in the content are replaced.
     * Refer to the RongCloud message type table for examples. If objectName is a custom message type, this parameter can be customized. (Required)
     * */
    private Map<String, String> template;
    /**
     * Key: User ID, Value: Template assignment content
     *
     * */
    private Map<String, TemplateMessage.Data> content;

    private String[] pushData;

    private String[] pushExt;

    private Integer verifyBlacklist;

    private Integer contentAvailable;

    private Long msgRandom;

    /**
     * Whether it is a silent message. Default is false. When set to true, end users will not receive notification reminders when offline. (Optional). Not supported in global data centers.
     */
    public Boolean disablePush;


    /**
     * Disable updating the last message in the conversation. When this parameter is false, the sent message will appear in the conversation list; when true, it will not update the conversation list's message content.
     * Note: This parameter only applies to messages stored on the client side.
     */
    private Boolean disableUpdateLastMsg;

    public String getSenderId() {
        return this.senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Map<String, String> getTemplate() {
        return template;
    }

    public void setTemplate(Map<String, String> template) {
        this.template = template;
    }

    public Map<String, TemplateMessage.Data> getContent() {
        return content;
    }

    public void setContent(Map<String, TemplateMessage.Data> content) {
        this.content = content;
    }

    public String[] getPushExt() {
        return this.pushExt;
    }

    public void setPushExt(String[] pushExt) {
        this.pushExt = pushExt;
    }

    public String[] getPushData() {
        return this.pushData;
    }

    public void setPushData(String[] pushData) {
        this.pushData = pushData;
    }

    public Integer getVerifyBlacklist() {
        return this.verifyBlacklist;
    }

    public void setVerifyBlacklist(Integer verifyBlacklist) {
        this.verifyBlacklist = verifyBlacklist;
    }

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public void setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
    }

    public Boolean getDisablePush() {
        return disablePush;
    }

    public void setDisablePush(Boolean disablePush) {
        this.disablePush = disablePush;
    }

    /**
     * The specific data of the template content, including message content and push content
     * @author RongCloud
     */
    public class Data {
        /**
         * The message content data, where the key corresponds to the template identifier and the value is the specific content
         */
        private Map<String, String> data;
        /**
         * The push content
         */
        private String push;

        public Map<String, String> getData() {
            return this.data;
        }

        public void setData(Map<String, String> data) {
            this.data = data;
        }

        public String getPush() {
            return this.push;
        }

        public void setPush(String push) {
            this.push = push;
        }
    }

    public Long getMsgRandom() {
        return msgRandom;
    }

    public void setMsgRandom(Long msgRandom) {
        this.msgRandom = msgRandom;
    }

    public Boolean getDisableUpdateLastMsg() {
        return disableUpdateLastMsg;
    }

    public TemplateMessage setDisableUpdateLastMsg(Boolean disableUpdateLastMsg) {
        this.disableUpdateLastMsg = disableUpdateLastMsg;
        return this;
    }
}
