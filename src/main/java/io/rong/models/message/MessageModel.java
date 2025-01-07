package io.rong.models.message;

import io.rong.util.GsonUtil;
import io.rong.messages.BaseMessage;


/**
 * 发送消息的消息体
 *
 * @author RongCloud
 */
public class MessageModel {

    private String senderId;
    /**
     * 接受 Id 可能是用户Id，聊天Id ，群组Id，讨论组Id（必传）
     **/
    private String[] targetId;
    /**
     * 消息类型 （必传）
     **/
    private String objectName;
    /**
     * 发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）。
     **/
    private BaseMessage content;
    /**
     * 定义显示的 Push 内容，如果 objectName 为融云内置消息类型时， 则发送后用户一定会收到 Push 信息。如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到
     * Push 通知。（可选）
     */
    private String pushContent;
    /**
     * 针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。（可选）
     */
    private String pushData;

    /**
     * 推送通知属性设置，详细查看 pushExt 结构说明，pushExt 为 JSON 结构请求时需要做转义处理。（可选）
     */
    private String pushExt;

    /**
     * 幂等性功能提示：
     *
     * 幂等性功能需要开通后才能使用，如有需要可提交工单申请，默认 1 分钟内设置的
     * msgRandom 标识重复，则返回错误码提示，超过 1 分钟后，服务端不再进行幂等性处理，但融云 SDK 仍然会做幂等性的保障处理。
     */
    private Long msgRandom;

    /**
     * 禁止更新会话最后一条消息。 当该参数为 false 时，发送的该条消息都会进入会话列表; 为 true 时，不会更新到会话列表的消息内容。
     * 注：此参数仅对存储在客户端的消息有效。
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
     * 此属性已经不再使用了，消息类型改为通过 BaseMessage 里的 getType 获取
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
     * 获取PushExt json参数
     *
     * @param pe  构建的 PushExt 对象
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
