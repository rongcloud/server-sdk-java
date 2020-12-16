package io.rong.models.message;

import com.google.gson.Gson;
import io.rong.util.GsonUtil;
import io.rong.messages.BaseMessage;

import java.util.ArrayList;
import java.util.List;

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
}
