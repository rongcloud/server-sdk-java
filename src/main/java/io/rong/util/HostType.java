package io.rong.util;

public class HostType {

    private String type;
    private String ip;
    private int connectTimeout = 30000;
    private int readTimeout = 30000;

    public HostType(String type) {
        this.type = type;
    }

    public String getStrType() {
        return type;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}