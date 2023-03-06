package io.rong.models.conversation;

/**
 * @author RongCloud
 */
public class ConversationModel {
    /**
     * 会话类型。支持的会话类型包括：1（二人会话）、3（群组会话）、6（系统会话）、10（超级群会话）。
     */
    public String type;

    /**
     * 设置消息免打扰的用户 ID。
     */
    public String userId;

    /**
     * 目标 ID，根据不同的会话类型（ConversationType），可能是用户 ID、群组 ID、超级群 ID 等。
     */
    public String targetId;
    /**
     * 超级群的会话频道 ID。
     * 如果传入频道 ID，则针对该指定频道设置消息免打扰级别。
     * 注意：2022.09.01 之前开通超级群业务的客户，如果不指定频道 ID，则默认传 “” 空字符串，即仅针对指定超级群会话（targetId）中不属于任何频道的消息设置免打扰状态级别。如需修改请提交工单。
     */
    public String busChannel;

    /**
     * -1： 全部消息通知
     * 0： 未设置（用户未设置情况下，默认以群 或者 APP级别的默认设置为准，如未设置则全部消息都通知）
     * 1： 仅针对 @ 消息进行通知
     * 2： 仅针对 @ 指定用户进行通知
     * 如：@张三 则张三可以收到推送，@所有人 时不会收到推送。
     * 4： 仅针对 @ 群全员进行通知，只接收 @所有人 的推送信息。
     * 5： 不接收通知
     * 注意：IMKit 5.2.1 及之前版本不支持 -1、2、4、5，具体表现为 -1、2、4 无法弹出本地通知，5 不生效。推荐 IMKit 用户升级到 5.2.2 及之后版本。
     */
    public int unpushLevel;

    public ConversationModel() {
    }

    /**
     * 构造函数。
     *
     * @param type:会话类型。
     * @param userId:设置消息免打扰的用户 Id
     * @param targetId:目标Id
     *
     **/
    public ConversationModel(String type, String userId, String targetId) {
        this.type = type;
        this.userId = userId;
        this.targetId = targetId;
    }

    public String getType() {
        return this.type;
    }

    public ConversationModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getUserId() {
        return this.userId;
    }

    public ConversationModel setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public ConversationModel setTargetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public String getBusChannel() {
        return busChannel;
    }

    public ConversationModel setBusChannel(String busChannel) {
        this.busChannel = busChannel;
        return this;
    }

    public int getUnpushLevel() {
        return unpushLevel;
    }

    public ConversationModel setUnpushLevel(int unpushLevel) {
        this.unpushLevel = unpushLevel;
        return this;
    }
}
