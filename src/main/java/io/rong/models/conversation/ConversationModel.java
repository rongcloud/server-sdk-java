package io.rong.models.conversation;

/**
 * @author RongCloud
 */
public class ConversationModel {
    /**
     * Conversation type. Supported conversation types include: 1 (one-to-one chat), 3 (group chat), 6 (system conversation), 10 (ultra group chat).
     */
    public String type;

    /**
     * User ID for setting message Do Not Disturb.
     */
    public String userId;

    /**
     * Target ID, which can be a user ID, group ID, ultra group ID, etc., depending on the conversation type (ConversationType).
     */
    public String targetId;
    /**
     * Conversation channel ID for ultra group.
     * If a channel ID is provided, the Do Not Disturb level will be set for the specified channel.
     * Note: For customers who enabled ultra group services before 2022.09.01, if no channel ID is specified, an empty string "" is passed by default, meaning the Do Not Disturb level will only be set for messages in the specified ultra group conversation (targetId) that do not belong to any channel. To modify this, please submit a ticket.
     */
    public String busChannel;

    /**
     * -1: Notify for all messages
     * 0: Not set (if the user has not set this, it defaults to the group or APP-level default settings. If not set, all messages will be notified)
     * 1: Notify only for @ messages
     * 2: Notify only for @ messages targeting specific users
     * Example: @ZhangSan will notify Zhang San, but @All will not trigger a notification.
     * 4: Notify only for @ messages targeting all group members, i.e., only receive notifications for @All.
     * 5: Do not receive notifications
     * Note: IMKit versions 5.2.1 and earlier do not support -1, 2, 4, and 5. Specifically, -1, 2, and 4 will not trigger local notifications, and 5 will not take effect. It is recommended that IMKit users upgrade to version 5.2.2 or later.
     */
    public int unpushLevel;

    public ConversationModel() {
    }

    /**
     * Constructor.
     *
     * @param type: Conversation type.
     * @param userId: User ID for setting message Do Not Disturb.
     * @param targetId: Target ID.
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
