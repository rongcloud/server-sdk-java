package io.rong.models.message;

/**
 *
 * @author rongcloud
 */
public class MentionedInfo {
    private int type;
    private String[] userIds;
    private String pushContent;

    public MentionedInfo(int type, String[] userIds, String pushContent) {
        this.type = type;
        this.userIds = userIds;
        this.pushContent = pushContent;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String[] getUserIds() {
        return this.userIds;
    }

    public void setUserIds(String[] userIds) {
        this.userIds = userIds;
    }

    public String getPushContent() {
        return this.pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent;
    }
}
