package io.rong.exception;

/**
 * Created with hc.
 * User: hc
 * Date: 17-12-19
 * Time: 下午4:45
 */
public abstract class RcloudException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = -700374663662873165L;
    protected Error error = null;
    public RcloudException (){
    }

    public RcloudException(String message,Throwable e) {
        super(message,e);
    }

    public RcloudException(Throwable e){
        super(e);
    }

    public RcloudException(String message){
        super(message);
    }

    public Error getError(){
        return error;
    }

    public int getErrorCode(){
        if (error == null){
            return 200;
        }
        return error.code;
    }

    public int getHttpCode(){
        if (error == null){
            return 200;
        }
        return error.httpCode;
    }

    public void setUri(String uri){
        if (error == null){
            return ;
        }
        error.url = uri;
    }
}

