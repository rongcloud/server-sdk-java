package io.rong.messages;

/**
 * User information carried in the message.
 */
public class UserInfo {
    private String id;
    private String name;
    private String portrait;
    private String extra;

    public UserInfo() {
    }
    public UserInfo(String id, String name,String portrait,String extra) {
        this.id = id;
        this.name = name;
        this.portrait = portrait;
        this.extra = extra;
    }
    /**
     * Gets the sender's user ID.
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the sender's user ID.
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the sender's display name.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the sender's display name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the sender's display portrait.
     * @return
     */
    public String getPortrait() {
        return portrait;
    }

    /**
     * Sets the sender's display portrait.
     * @param portrait
     */
    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }


    /**
     * Gets the extended information.
     * @return
     */
    public String getExtra() {
        return extra;
    }

    /**
     * Sets the extended information.
     * @param extra
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }
}
