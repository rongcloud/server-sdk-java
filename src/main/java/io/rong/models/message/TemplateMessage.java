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
    private Object template;
    /**
     * key 用户Id ,value 模板赋值内容
     *
     * */
    private Map<String, TemplateMessage.Data> content;

    private String[] pushData;

    private Integer verifyBlacklist;

    private Integer contentAvailable;
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
    public Object getTemplate() {
        return this.template;
    }
    /**
     * 设置 template
     */
    public void setTemplate(Object template) {
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
}
