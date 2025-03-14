package io.rong.models.ultragroup;

import java.util.List;

/**
 * Ultra Group Service
 */
public class UltraGroupModel {
    /**
     * Group ID
     **/
    private String id;
    /**
     * Group members
     **/
    private UltraGroupMember[] members;
    /**
     * Group name
     **/
    private String name;

    /**
     * Mute status
     * */
    private Integer status;

    /**
     * Admin user ID
     * */
    private String userId;

    /**
     * Channel type
     * */
    private int type;

    /**
     * Group channel list
     **/
    private String busChannel;

    public UltraGroupModel() {
    }
    /**
     * Constructor
     *
     * @param id Group ID
     * @param members Group members
     * @param name Group name
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
