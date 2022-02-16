package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * 超级群消息体
 *
 */
public class UltraGroupMessage extends MessageModel {

    /**
     * 针对 iOS 平台，Push 时用来控制未读消息显示数，只有在 toUserId 为一个用户 Id 的时候有效。（可选）
     */
    public Integer isPersisted;

    /**
     * ios静默推送 0关闭 1开启
     **/
    public Integer contentAvailable;

    /**
     * 频道ID，发消息时会对群 ID 下的频道 ID 做合法性校验，如果群 ID 下无此频道 ID 则消息发送终止, 参数合法性校验: a-zA-Z0-9, 禁止包含其它字符，下划线也不行，最长 20 个字符。
     */
    public String busChannel;

    public UltraGroupMessage() {
    }

    /**
     * @param senderId:发送人用户 Id 。（必传）
     * @param targetId:接收群Id，提供多个本参数可以实现向多群发送消息，最多不超过 3 个群组。（必传）
     * @param content:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）
     * @param pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息. 如果为自定义消息，则 pushContent 为自定义消息显示的
     *                    Push 内容，如果不传则用户不会收到 Push 通知。（可选）
     * @param pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。（可选）
     * @param isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。（可选）
     */
    public UltraGroupMessage(String senderId, String[] targetId, String objectName, BaseMessage content, String pushContent,
                             String pushData, Integer isPersisted, Integer contentAvailable) {
        super(senderId, targetId, objectName, content, pushContent, pushData);
        this.isPersisted = isPersisted;
        //this.isCounted = isCounted;
        this.contentAvailable = contentAvailable;
    }

    public UltraGroupMessage(String senderId, String[] targetId, String objectName, BaseMessage content, String pushContent,
                             String pushData, String pushExt, Integer isPersisted, Integer contentAvailable) {
        super(senderId, targetId, objectName, content, pushContent, pushData, pushExt);
        this.isPersisted = isPersisted;
        this.contentAvailable = contentAvailable;
    }

    public UltraGroupMessage(String senderId, String[] targetId, String objectName, BaseMessage content, String pushContent,
                             String pushData, Integer isPersisted, Integer contentAvailable,
                             String busChannel) {
        super(senderId, targetId, objectName, content, pushContent, pushData);
        this.isPersisted = isPersisted;
        this.contentAvailable = contentAvailable;
        this.busChannel = busChannel;
    }

    public UltraGroupMessage(String senderId, String[] targetId, String objectName, BaseMessage content, String pushContent,
                             String pushData, String pushExt, Integer isPersisted,
                             Integer contentAvailable, String busChannel) {
        super(senderId, targetId, objectName, content, pushContent, pushData, pushExt);
        this.isPersisted = isPersisted;
        this.contentAvailable = contentAvailable;
        this.busChannel = busChannel;
    }

    @Override
    public UltraGroupMessage setSenderId(String senderId) {
        super.setSenderId(senderId);
        return this;
    }

    /**
     * 获取接收群组Id
     *
     * @return String
     */
    @Override
    public String[] getTargetId() {
        return super.getTargetId();
    }

    /**
     * 设置接收群组Id
     *
     * @return String
     */
    @Override
    public UltraGroupMessage setTargetId(String[] targetId) {
        super.setTargetId(targetId);
        return this;
    }

    @Override
    public UltraGroupMessage setContent(BaseMessage content) {
        super.setContent(content);
        return this;
    }

    @Override
    public UltraGroupMessage setPushContent(String pushContent) {
        super.setPushContent(pushContent);
        return this;
    }

    @Override
    public UltraGroupMessage setPushData(String pushData) {
        super.setPushData(pushData);
        return this;
    }

    @Override
    public UltraGroupMessage setPushExt(String pushExt) {
        super.setPushExt(pushExt);
        return this;
    }

    @Override
    public UltraGroupMessage setPushExt(PushExt pe) {
        super.setPushExt(pe);
        return this;
    }

    public Integer getIsPersisted() {
        return this.isPersisted;
    }

    public UltraGroupMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    /*public Integer getIsCounted() {
        return this.isCounted;
    }

    public GroupMessage setIsCounted(Integer isCounted) {
        this.isCounted = isCounted;
        return this;
    }*/

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public UltraGroupMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }

    @Override
    public UltraGroupMessage setObjectName(String objectName) {
        super.setObjectName(objectName);
        return this;
    }

    public String getBusChannel() {
        return busChannel;
    }

    public void setBusChannel(String busChannel) {
        this.busChannel = busChannel;
    }

}
