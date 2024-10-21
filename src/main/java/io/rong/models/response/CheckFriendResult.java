package io.rong.models.response;

/**
 * @author RongCloud
 */
public class CheckFriendResult {
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 单向检查返回状态：
     * 1：不在我的好友列表中
     * 2：在我的好友列表中
     * 双向检查返回状态：
     * 1：在双方的好友列表中
     * 2：不在双方的好友列表中
     * 3：仅在当前用户的好友列表中
     * 4：仅在目标用户的好友列表中
     */
    private Integer result;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
