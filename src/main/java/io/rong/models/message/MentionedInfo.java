package io.rong.models.message;

/**
 *
 * @author rongcloud
 */
public class MentionedInfo {
    private int type;
    private String[] userIdList;
    private String mentionedContent;

    public MentionedInfo(int type, String[] userIds, String mentionedContent) {
        this.type = type;
        this.userIdList = userIds;
        this.mentionedContent = mentionedContent;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String[] getUserIdList() {
        return this.userIdList;
    }

    public void setUserIdList(String[] userIdList) {
        this.userIdList = userIdList;
    }

    public String getMentionedContent() {
        return this.mentionedContent;
    }

    public void setMentionedContent(String mentionedContent) {
        this.mentionedContent = mentionedContent;
    }
}
