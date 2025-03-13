package io.rong.models.group;

/**
 * Group data model
 *
 * @author Rongcloud
 * */
public class GroupModel {
    /**
     * Group ID
     **/
    private String id;
    /**
     * Group members
     **/
    private GroupMember[] members;
    /**
     * Group name
     **/
    private String name;

    /**
     * Mute duration in minutes
     * */
    private Integer minute;
    /**
     * Mute status
     * */
    private Integer status;

    // Group operation notification message. Only valid for create, join, quit, and dismiss operations.
    /**
     * Current group ID, maximum number of group members. Defaults to the app's unified group member limit, not exceeding it. This field is only valid for the current request and is not stored by the IM server.
     */
    private int maxMember;
    /**
     * Whether to bind the notification message and not deliver the corresponding message.
     */
    private boolean bindNotifyMsg;
    private String fromUserId;
    private String objectName;
    private String content;
    private String pushContent;
    private String pushData;
    private int isIncludeSender = 0;
    private int isPersisted = 0;
    private String pushExt = "";




    public GroupModel() {
    }
    /**
     * Constructor
     *
     * @param id The group ID
     * @param members The group members
     * @param name The group name
     */
    public GroupModel(String id, GroupMember[] members, String name, Integer minute) {
        this.id = id;
        this.members = members;
        this.name = name;
        this.minute = minute;
    }
    public GroupModel(String id, Integer status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return this.id;
    }

    public GroupModel setId(String id) {
        this.id = id;
        return this;
    }

    public GroupMember[] getMembers() {
        return this.members;
    }

    public GroupModel setMembers(GroupMember[] members) {
        this.members = members;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public GroupModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getMinute() {
        return this.minute;
    }

    public GroupModel setMinute(Integer minute) {
        this.minute = minute;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public GroupModel setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public int getMaxMember() {
        return maxMember;
    }

    public GroupModel setMaxMember(int maxMember) {
        this.maxMember = maxMember;
        return this;
    }

    public boolean isBindNotifyMsg() {
        return bindNotifyMsg;
    }

    public GroupModel setBindNotifyMsg(boolean bindNotifyMsg) {
        this.bindNotifyMsg = bindNotifyMsg;
        return this;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public GroupModel setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }

    public String getObjectName() {
        return objectName;
    }

    public GroupModel setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public String getContent() {
        return content;
    }

    public GroupModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getPushContent() {
        return pushContent;
    }

    public GroupModel setPushContent(String pushContent) {
        this.pushContent = pushContent;
        return this;
    }

    public String getPushData() {
        return pushData;
    }

    public GroupModel setPushData(String pushData) {
        this.pushData = pushData;
        return this;
    }

    public int getIsIncludeSender() {
        return isIncludeSender;
    }

    public GroupModel setIsIncludeSender(int isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
        return this;
    }

    public int getIsPersisted() {
        return isPersisted;
    }

    public GroupModel setIsPersisted(int isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    public String getPushExt() {
        return pushExt;
    }

    public GroupModel setPushExt(String pushExt) {
        this.pushExt = pushExt;
        return this;
    }
}
