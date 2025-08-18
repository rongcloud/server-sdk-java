package io.rong.models.response;


import io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class ChatAgentResult extends ResponseResult {
    public ChatAgentResult(Integer code, String msg) {
        super(code, msg);
    }

    private String runId;
    private String conversationId;
    private String agentId;
    private Long timestamp;
    private String answer;
    private String finishReason;
    private ChatMetadata metadata;

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    public ChatMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(ChatMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ChatAgentResult.class);
    }
}
