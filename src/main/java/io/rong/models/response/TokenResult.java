package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * Result of getToken
 */
public class TokenResult extends Result {
    // User Token, which can be stored within the application and has a length of up to 256 bytes.
    String token;
    // User ID, which is the same as the input User ID.
    String userId;

    private String reqBody;

    public TokenResult(Integer code, String token, String userId, String errorMessage) {
        this.code = code;
        this.token = token;
        this.userId = userId;
        this.errorMessage = errorMessage;
    }

    /**
     * Set the token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Get the token
     *
     * @return String
     */
    public String getToken() {
        return token;
    }

    /**
     * Set the userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Get the userId
     */

    /**
     * @return String
     */
    public String getUserId() {
        return userId;
    }


    public String getReqBody() {
        return reqBody;
    }


    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, TokenResult.class);
    }
}
