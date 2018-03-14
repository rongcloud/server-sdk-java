package io.rong.exception;



public class ParamException extends RcloudException{

    private static final long serialVersionUID = -5021603276540528761L;

    public ParamException(){
        this.error = new ParamError("/");
    }

    public ParamException(String message, Throwable e) {
        super(new ParamError("/",message).toString(),e);
        this.error = new ParamError("/",message);
    }

    public ParamException(Throwable e){
        super(e);
        this.error = new ParamError("/");
    }

    public ParamException(String message){
        super(message);
        this.error = new ParamError("/",message);
    }

    public ParamException(int errorCode, String apiUrl, String message){
        super(new ParamError(errorCode,apiUrl,message).toString());
        this.error = new ParamError(errorCode,apiUrl,message);

    }


    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}

