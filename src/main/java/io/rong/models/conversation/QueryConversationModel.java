package io.rong.models.conversation;

/**
 * Parameters for querying a user's conversations.
 */
public class QueryConversationModel {

    /** User ID. Required. */
    private String userId;

    /** Next page token from the previous response. Omit for the first page. */
    private String pageToken;

    /** Page size. The server defaults to 200 and validates the range 1-200. */
    private Integer size;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
