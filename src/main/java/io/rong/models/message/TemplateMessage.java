package io.rong.models.message;

import java.util.Map;

/**
 * 模板消息体
 * @author hc
 */
public class TemplateMessage {
    /**
     * 发送人Id
     * */
    private String senderId;
    /**
     * 消息类型
     * */
    private String objectName;
    /**
     * 发送消息内容，内容中定义模版，标识通过 content 中的标识位内容进行替换，
     * 参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）
     * */
    private Map<String,String> template;
    /**
     * key 用户Id ,value 模板赋值内容
     *
     * */
    private Map<String, TemplateMessage.Data> content;

    private String[] pushData;

    private String[] pushExt;

    private Integer verifyBlacklist;

    private Integer contentAvailable;

    private Long msgRandom;

    /**
     * 是否为静默消息，默认为 false，设为 true 时终端用户离线情况下不会收到通知提醒（可选）。暂不支持海外数据中心
     */
    public Boolean disablePush;


    /**
     * 禁止更新会话最后一条消息。 当该参数为 false 时，发送的该条消息都会进入会话列表; 为 true 时，不会更新到会话列表的消息内容。
     * 注：此参数仅对存储在客户端的消息有效。
     */
    private Boolean disableUpdateLastMsg;

    /**
     * 获取senderId
     * @return String
     */
    public String getSenderId() {
        return this.senderId;
    }
    /**
     * 设置 senderId
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    /**
     * 获取objectName
     * @return String
     */
    public String getObjectName() {
        return this.objectName;
    }
    /**
     * 设置 objectName
     */
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
    /**
     * 获取template
     * @return String
     */
    public Map<String, String> getTemplate() {
        return template;
    }
    /**
     * 设置 template
     */
    public void setTemplate(Map<String, String> template) {
        this.template = template;
    }
    public Map<String, TemplateMessage.Data> getContent() {
        return content;
    }
    /**
     * 设置 content
     */
    public void setContent(Map<String, TemplateMessage.Data> content) {
        this.content = content;
    }
    /**
     * 获取pushExt
     * @return String
     */
    public String[] getPushExt() {
        return this.pushExt;
    }
    /**
     * 设置 pushExt
     */
    public void setPushExt(String[] pushExt) {
        this.pushExt = pushExt;
    }

    /**
     * 获取pushData
     * @return String
     */
    public String[] getPushData() {
        return this.pushData;
    }
    /**
     * 设置 pushData
     */
    public void setPushData(String[] pushData) {
        this.pushData = pushData;
    }
    /**
     * 获取verifyBlacklist
     * @return String
     */
    public Integer getVerifyBlacklist() {
        return this.verifyBlacklist;
    }
    /**
     * 设置 verifyBlacklist
     */
    public void setVerifyBlacklist(Integer verifyBlacklist) {
        this.verifyBlacklist = verifyBlacklist;
    }
    /**
     * 获取contentAvailable
     * @return String
     */
    public Integer getContentAvailable() {
        return this.contentAvailable;
    }
    /**
     * 设置 contentAvailable
     */
    public void setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
    }

    public Boolean getDisablePush() {   return disablePush;  }

    public void setDisablePush(Boolean disablePush) {
        this.disablePush = disablePush;
    }

    /**
     * 模版内容的具体数据，包括消息内容和push内容
     * @author RongCloud
     */
    public class Data{
        /**
         * 消息内容数据，key对应模版的标识 ，value具体内容
         */
        private Map<String,String> data;
        /**
         * push内容
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
