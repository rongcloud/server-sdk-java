package io.rong.models.response;

/**
 * @author RongCloud
 */
public class CheckFriendResult {
    /**
     * User ID
     */
    private String userId;
    /**
     * Unidirectional check result:
     * 1: Not in my friend list
     * 2: In my friend list
     * Bidirectional check result:
     * 1: In both users' friend lists
     * 2: Not in both users' friend lists
     * 3: Only in the current user's friend list
     * 4: Only in the target user's friend list
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
