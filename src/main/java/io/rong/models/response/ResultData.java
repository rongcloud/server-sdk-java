package io.rong.models.response;

/**
 * @author RongCloud
 */
public class ResultData<T> extends ResponseResult{

    private T data;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultData(Integer code, String msg) {
        super(code, msg);
    }

}
