package io.rong.models.ultragroup;

/**
 * @author RongCloud
 */
public class UserUltraGroup {

    private String id;
    private UltraGroupModel[] groups;

    public UserUltraGroup() {
    }

    public UserUltraGroup(String id, UltraGroupModel[] groups) {
        this.id = id;
        this.groups = groups;
    }

    public String getId() {
        return this.id;
    }

    public UserUltraGroup setId(String id) {
        this.id = id;
        return this;
    }

    public UltraGroupModel[] getGroups() {
        return this.groups;
    }

    public UserUltraGroup setGroups(UltraGroupModel[] groups) {
        this.groups = groups;
        return this;
    }
}
