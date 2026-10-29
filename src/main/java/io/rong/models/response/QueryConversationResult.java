package io.rong.models.response;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * A page of user conversations.
 */
public class QueryConversationResult extends ResponseResult {

    private List<Conversation> conversations;

    /** Next page token; absent on the last page. */
    private String pageToken;

    public QueryConversationResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public String getPageToken() {
        return pageToken;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, QueryConversationResult.class);
    }

    /** Conversation information returned by the server. */
    public static class Conversation {
        private String targetId;
        private String busChannel;
        private Integer conversationType;
        private Long version;

        public String getTargetId() {
            return targetId;
        }

        public String getBusChannel() {
            return busChannel;
        }

        public Integer getConversationType() {
            return conversationType;
        }

        public Long getVersion() {
            return version;
        }
    }
}
