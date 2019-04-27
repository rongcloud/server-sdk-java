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
}
