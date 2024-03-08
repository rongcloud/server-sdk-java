package io.rong.models.response.group;

import io.rong.util.GsonUtil;
import io.rong.models.response.ResponseResult;

/**
 * 特别关注群成员获取接口返回实体
 */
public class AttentionResult extends ResponseResult {
    private String userId;
    private String groupId;
    private AttentionMembers[] attentionList;

    public AttentionResult(Integer code, String msg) {
        super(code, msg);
        this.code = code;
        this.errorMessage = msg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public AttentionMembers[] getAttentionList() {
        return attentionList;
    }

    public void setAttentionList(AttentionMembers[] attentionList) {
        this.attentionList = attentionList;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, AttentionResult.class);
    }
}
