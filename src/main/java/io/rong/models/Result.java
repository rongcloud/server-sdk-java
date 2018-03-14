package io.rong.models;
/**
 *  基础 http 成功返回结果
 */
public abstract class Result {
    /**
     * 返回码，200 为正常。
     *
     */
    public Integer code;
    /**
     * 错误信息。
     *
     */
    public String msg;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result() {

    }
    /**
     * 设置code
     *
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取code
     *
     * @return Integer
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 获取msg
     *
     * @return String
     */
    public String getMsg() {
        return this.msg;
    }
    /**
     * 设置msg
     *
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public abstract String toString();
}
