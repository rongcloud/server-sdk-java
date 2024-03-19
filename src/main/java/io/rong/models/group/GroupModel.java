package io.rong.models.group;

/**
 * 群组数据模型
 *
 * @author Rongcloud
 * */
public class GroupModel {
    /**
     * 群组id
     **/
    private String id;
    /**
     * 群组成员
     **/
    private GroupMember[] members;
    /**
     * 群组名
     **/
    private String name;

    /**
     * 禁言时间
     * */
    private Integer minute;
    /**
     * 禁言状态
     * */
    private Integer status;

    //群操作通知消息 仅对 创建 加入 退出 解散 操作有效
    /**
     * 当前群ID,最大群成员数量, 默认与APP 统一群成员数量限制一致, 不大于APP 统一群成员数量限制. 本字段仅当次请求有效. IM 服务端不做存储记录.
     */
    private int maxMember;
    /**
     * 是否绑定通知消息, 不下发对应消息,
     */
    private boolean bindNotifyMsg;
    private String fromUserId;
    private String objectName;
    private String content;
    private String pushContent;
    private String pushData;
    private Integer isIncludeSender = 0;
    private Integer isPersisted = 0;
    private String pushExt = "";


    /**
     * 是否同时移除群成员禁言状态(退出群组功能时使用)：
     * 默认为 0 不移除；
     * 为 1 时从该群的禁言成员单列表中删除
     */
    private int isQuitBan = 0;
    /**
     * 是否同时移除群禁言白名单(退出群组功能时使用)：
     * 默认为 0 不移除；
     * 为 1 时从该群的禁言白
     */
    private int isQuitWhite = 0;


    public GroupModel() {
    }
    /**
     * 构造方法
     *
     * @param id 群组id
     * @param members 群组成员
     * @param name 群名
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

    public Integer getIsQuitBan() {
        return isQuitBan;
    }

    public GroupModel setIsQuitBan(int isQuitBan) {
        this.isQuitBan = isQuitBan;
        return this;
    }

    public Integer getIsQuitWhite() {
        return isQuitWhite;
    }

    public GroupModel setIsQuitWhite(int isQuitWhite) {
        this.isQuitWhite = isQuitWhite;
        return this;
    }
}
