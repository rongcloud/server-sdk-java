package io.rong.models.message;

import java.util.List;

/**
 * 消息批量撤回
 *
 */
public class BatchRecallMessage {

    private List<RecallMessage> recallMessages;

    public BatchRecallMessage(List<RecallMessage> recallMessages) {
        this.recallMessages = recallMessages;
    }

    public List<RecallMessage> getRecallMessages() {
        return recallMessages;
    }

    public BatchRecallMessage setRecallMessages(List<RecallMessage> recallMessages) {
        this.recallMessages = recallMessages;
        return this;
    }
}
