package io.rong.models.message;

public class RecallMessage {
    /**
     * 撤回消息体
     * 发送人id
     */
    public String senderId;
    /**
     * 接收人id
     */
    public String targetId;
    /**
     * 消息唯一标识 各端 SDK 发送消息成功后会返回 uId
     */
    public String uId;
    /**
     * 消息的发送时间，各端 SDK 发送消息成功后会返回 sentTime（可选）。
     */
    public String sentTime;

    /**
     * 是否为管理员，默认为 0，设为 1 时，IMKit 收到此条消息后，小灰条默认显示为“管理员 撤回了一条消息”（可选）。
     */
    public Integer isAdmin;

    /**
     * 默认为 0 撤回该条消息同时，用户端将该条消息删除并替换为一条小灰条撤回提示消息；为 1 时，该条消息删除后，不替换为小灰条提示消息（可选）。
     */
    public Integer isDelete;

    /**
     * 扩展信息，可以放置任意的数据内容（可选）。
     */
    public String extra;

    /**
     * 是否为静默消息，默认为 false，设为 true 时终端用户离线情况下不会收到通知提醒（可选）。暂不支持海外数据中心
     */
    public Boolean disablePush;

    /**
     * 超级群频道 ID，仅适用于撤回超级群消息。使用要求如下：
     * 如果发送消息时指定了频道 ID，则撤回时必须指定频道 ID，否则无法撤回。
     * 如果发送消息时未指定频道 ID，则撤回时不可指定频道 ID，否则无法撤回。
     * 客户端发送超级群消息时，频道 ID 对应字段名称为 channelId。
     */
    private  String busChannel;

    /**
     * 禁止更新会话最后一条消息。 当该参数为 false 时，发送的该条消息都会进入会话列表; 为 true 时，不会更新到会话列表的消息内容。
     * 注：此参数仅对存储在客户端的消息有效。
     */
    private Boolean disableUpdateLastMsg;

    public RecallMessage() {
    }

    /**
     * @param senderId         String	消息发送人用户 Id。（必传）
     * @param conversationType Int	会话类型，二人会话是 1 、讨论组会话是 2 、群组会话是 3 。（必传）
     * @param targetId         String	目标 Id，根据不同的 ConversationType，可能是用户 Id、讨论组 Id、群组 Id。（必传）
     * @param uId              String	消息唯一标识，可通过服务端实时消息路由获取，对应名称为 msgUID。（必传）
     * @param sentTime
     */
    public RecallMessage(String senderId, String conversationType, String targetId,
                         String uId, String sentTime) {
        this.senderId = senderId;
        this.targetId = targetId;
        this.uId = uId;
        this.sentTime = sentTime;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public RecallMessage setSenderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public RecallMessage setTargetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public String getUId() {
        return uId;
    }

    public RecallMessage setuId(String uId) {
        this.uId = uId;
        return this;
    }

    public String getSentTime() {
        return this.sentTime;
    }

    public RecallMessage setSentTime(String sentTime) {
        this.sentTime = sentTime;
        return this;
    }

    public Boolean getDisablePush() {
        return disablePush;
    }

    public RecallMessage setDisablePush(Boolean disablePush) {
        this.disablePush = disablePush;
        return this;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public RecallMessage setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public RecallMessage setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public String getExtra() {
        return extra;
    }

    public RecallMessage setExtra(String extra) {
        this.extra = extra;
        return this;
    }

    public String getBusChannel() {
        return busChannel;
    }

    public RecallMessage setBusChannel(String busChannel) {
        this.busChannel = busChannel;
        return this;
    }


    public Boolean getDisableUpdateLastMsg() {
        return disableUpdateLastMsg;
    }

    public RecallMessage setDisableUpdateLastMsg(Boolean disableUpdateLastMsg) {
        this.disableUpdateLastMsg = disableUpdateLastMsg;
        return this;
    }
}
