package io.rong.models;
/**
 * Base HTTP success response result.
 */
public abstract class Result {
    /**
     * Response code, 200 indicates success.
     */
    public Integer code;
    /**
     * Error message.
     */
    public String errorMessage;

    public String requestId;

    public Result(Integer code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public Result() {

    }
    /**
     * Sets the response code.
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Gets the response code.
     *
     * @return Integer
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Gets the error message.
     *
     * @return String
     */
    public String getErrorMessage() {
        return errorMessage;
    }
    /**
     * Set the error message
     *
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public abstract String toString();
}
