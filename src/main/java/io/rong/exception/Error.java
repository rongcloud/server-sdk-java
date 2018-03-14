package io.rong.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import io.rong.util.GsonUtil;

/**
 * Created with IntelliJ IDEA.
 * User: srp
 * Date: 14-3-17
 * Time: 下午6:12
 */
@XStreamAlias("error")
public class Error{
    protected String url;
    @JsonIgnore
    protected transient int httpCode = 200;
    protected int code;
    protected String errorMessage;

    public Error(int code,int httpCode, String url, String errorMessage){
        this.url = url;
        this.code = code;
        this.errorMessage = errorMessage;
        this.httpCode = httpCode;
    }

    public String getUrl() {
        return this.url;
    }

    public Error setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    @JsonIgnore
    public int getHttpCode() {
        return this.httpCode;
    }
    @JsonIgnore
    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public boolean hasError(){
        return this.code != 200;
    }

    public ObjectNode toJsonObject(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode =objectMapper.createObjectNode();
        objectNode.put("url",this.url);
        objectNode.put("code",this.code);
        objectNode.put("errorMessage",this.errorMessage);
        return objectNode;
    }


    public String toXML(){
        XStream xstream = new XStream();
        xstream.processAnnotations(this.getClass());
        return xstream.toXML(this);
    }
    @Override
    public String toString(){
        return GsonUtil.toJson(this, Error.class);
    }
}
