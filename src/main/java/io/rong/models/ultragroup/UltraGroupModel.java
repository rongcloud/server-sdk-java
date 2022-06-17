package io.rong.models.ultragroup;

import java.util.List;

/**
 * 超级群服务
 * https://doc.rongcloud.cn/imserver/server/v1/ultragroup/basic
 */
public class UltraGroupModel {
    /**
     * 群组id
     **/
    private String id;
    /**
     * 群组成员
     **/
    private UltraGroupMember[] members;
    /**
     * 群组名
     **/
    private String name;

    /**
     * 禁言状态
     * */
    private Integer status;

    /**
     * 管理员 userId
     * */
    private String userId;

    /**
     * 频道类型
     * */
    private int type;

    /**
     * 群频道列表
     **/
    private String busChannel;

    public UltraGroupModel() {
    }
    /**
     * 构造方法
     *
     * @param id 群组id
     * @param members 群组成员
     * @param name 群名
     */
    public UltraGroupModel(String id, UltraGroupMember[] members, String name) {
        this.id = id;
        this.members = members;
        this.name = name;
    }
    public UltraGroupModel(String id, Integer status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return this.id;
    }

    public UltraGroupModel setId(String id) {
        this.id = id;
        return this;
    }

    public UltraGroupMember[] getMembers() {
        return this.members;
    }

    public UltraGroupModel setMembers(UltraGroupMember[] members) {
        this.members = members;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public UltraGroupModel setName(String name) {
        this.name = name;
        return this;
    }


    public Integer getStatus() {
        return status;
    }

    public UltraGroupModel setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getBusChannel() {
        return busChannel;
    }

    public UltraGroupModel setBusChannel(String busChannel) {
        this.busChannel = busChannel;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UltraGroupModel setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public  int getType() {
        return type;
    }

    public UltraGroupModel setType(int type) {
        this.type = type;
        return this;
    }
}
