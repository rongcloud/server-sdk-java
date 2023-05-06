package io.rong.models.message;

import io.rong.messages.BaseMessage;
import java.util.HashMap;

/**
 * 单聊 消息体
 *
 * @author RongCloud
 */
public class PrivateMessage extends MessageModel {

    /**
     * 针对 iOS 平台，Push 时用来控制未读消息显示数，只有在 toUserId 为一个用户 Id 的时候有效，客户端获取远程推送内容时为 badge，为 -1 时不改变角标数，传入相应数字表示把角标数改为指定的数字，最大不超过 9999。(可选)
     **/
    public String count;
    /**
     * 针对 iOS 平台，Push 时用来控制未读消息显示数，只有在 toUserId 为一个用户 Id 的时候有效。（可选）
     */
    public Integer isPersisted;
    /**
     * 当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数， 0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选）
     */
    public Integer isCounted;

    /**
     * 是否过滤发送人黑名单列表，0 表示为不过滤、 1 表示为过滤，默认为 0 不过滤。（可选
     */
    public Integer verifyBlacklist;
    /**
     * 发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。（可选）
     */
    public Integer isIncludeSender;

    public Integer contentAvailable;

    /**
     * 是否为静默消息，默认为 false，设为 true 时终端用户离线情况下不会收到通知提醒（可选）。暂不支持海外数据中心
     */
    public Boolean disablePush;

    /**
     * 是否为可扩展消息，默认为 false，设为 true 时终端在收到该条消息后，可对该条消息设置扩展信息（可选）。暂不支持海外数据中心
     */
    public Boolean expansion;

    /**
     * 扩展消息内容，expansion 为true 的时候生效
     */
    public HashMap<String, String> extraContent;

    public PrivateMessage() {
    }

    public PrivateMessage(String senderId, String[] targetId, String objectName, BaseMessage content,
        String pushContent, String pushData, String count, Integer isPersisted, Integer isCounted,
        Integer verifyBlacklist, Integer isIncludeSender, Integer contentAvailable) {
        super(senderId, targetId, objectName, content, pushContent, pushData);
        this.count = count;
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.verifyBlacklist = verifyBlacklist;
        this.isIncludeSender = isIncludeSender;
        this.contentAvailable = contentAvailable;
    }

    public PrivateMessage(String senderId, String[] targetId, String objectName, BaseMessage content,
        String pushContent, String pushData, String pushExt, String count, Integer isPersisted, Integer isCounted,
        Integer verifyBlacklist, Integer isIncludeSender, Integer contentAvailable) {
        super(senderId, targetId, objectName, content, pushContent, pushData, pushExt);
        this.count = count;
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.verifyBlacklist = verifyBlacklist;
        this.isIncludeSender = isIncludeSender;
        this.contentAvailable = contentAvailable;
    }

    @Override
    public PrivateMessage setSenderId(String senderId) {
        super.setSenderId(senderId);
        return this;
    }

    /**
     * 获取接受用户id
     *
     * @return String
     */
    @Override
    public String[] getTargetId() {
        return super.getTargetId();
    }

    /**
     * 设置接受用户id
     */
    @Override
    public PrivateMessage setTargetId(String[] targetId) {
        super.setTargetId(targetId);
        return this;
    }

    @Override
    public PrivateMessage setContent(BaseMessage content) {
        super.setContent(content);
        return this;
    }

    @Override
    public PrivateMessage setPushContent(String pushContent) {
        super.setPushContent(pushContent);
        return this;
    }

    @Override
    public PrivateMessage setPushData(String pushData) {
        super.setPushData(pushData);
        return this;
    }

    @Override
    public PrivateMessage setPushExt(String pushExt) {
        super.setPushExt(pushExt);
        return this;
    }

    @Override
    public PrivateMessage setPushExt(PushExt pe) {
        super.setPushExt(pe);
        return this;
    }

    public String getCount() {
        return this.count;
    }

    public PrivateMessage setCount(String count) {
        this.count = count;
        return this;
    }

    public Integer getVerifyBlacklist() {
        return this.verifyBlacklist;
    }

    public PrivateMessage setVerifyBlacklist(Integer verifyBlacklist) {
        this.verifyBlacklist = verifyBlacklist;
        return this;
    }

    public Integer getIsPersisted() {
        return this.isPersisted;
    }

    public PrivateMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    public Integer getIsCounted() {
        return this.isCounted;
    }

    public PrivateMessage setIsCounted(Integer isCounted) {
        this.isCounted = isCounted;
        return this;
    }

    public Integer getIsIncludeSender() {
        return this.isIncludeSender;
    }

    public PrivateMessage setIsIncludeSender(Integer isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
        return this;
    }

    public Boolean getDisablePush() {   return disablePush;  }

    public PrivateMessage setDisablePush(Boolean disablePush) {
        this.disablePush = disablePush;
        return this;
    }

    public Boolean getExpansion() {   return expansion;  }

    public PrivateMessage setExpansion(Boolean expansion) {
        this.expansion = expansion;
        return this;
    }

    @Override
    public PrivateMessage setObjectName(String objectName) {
        super.setObjectName(objectName);
        return this;
    }

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public PrivateMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }

    @Override
    public PrivateMessage setMsgRandom(Long msgRandom) {
        super.setMsgRandom(msgRandom);
        return this;
    }

    public HashMap<String, String> getExtraContent() {
        return extraContent;
    }

    public PrivateMessage setExtraContent(HashMap<String, String> extraContent) {
        this.extraContent = extraContent;
        return this;
    }
}
