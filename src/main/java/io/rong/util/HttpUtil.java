package io.rong.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.rong.RongCloudConfig;
import io.rong.models.response.ResponseResult;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.util.UUID;

/**
 * HTTP common service
 *
 * @author RongCloud
 */
public class HttpUtil {

    private static final String APPKEY = "App-Key";
    private static final String USERAGENT = "User-Agent";
    private static final String NONCE = "Nonce";
    private static final String TIMESTAMP = "Timestamp";
    private static final String SIGNATURE = "Signature";
    private static SSLContext sslCtx = null;

    static {

        try {
            sslCtx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            sslCtx.init(null, new TrustManager[]{tm}, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                // TODO Auto-generated method stub
                return true;
            }

        });

        HttpsURLConnection.setDefaultSSLSocketFactory(sslCtx.getSocketFactory());

    }

    public static HttpURLConnection CreateGetHttpConnection(String uri) throws MalformedURLException, IOException {
        URL url = new URL(uri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(30000);
        conn.setRequestMethod("GET");
        return conn;
    }

    // set body
    public static void setBodyParameter(String str, HttpURLConnection conn, RongCloudConfig config) throws IOException {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(conn.getOutputStream());
            out.write(str.getBytes("utf-8"));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            config.errorCounter.incrementAndGet();
            throw e;
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }

    public static HttpURLConnection CreatePostHttpConnection(RongCloudConfig config, String appKey, String appSecret,
                                                             String uri) throws MalformedURLException, IOException, ProtocolException {
        return CreatePostHttpConnection(config, appKey, appSecret, uri, "application/x-www-form-urlencoded");
    }

    public static HttpURLConnection CreatePostHttpConnection(RongCloudConfig config, String appKey, String appSecret,
                                                             String uri, String contentType) throws IOException {
        HttpURLConnection conn = createHttpConnection(config, appKey, appSecret, uri, "POST");
        conn.setRequestProperty("Content-Type", contentType);
        return conn;
    }

    public static HttpURLConnection createGetHttpConnection(RongCloudConfig config, String appKey, String appSecret, String uri) throws IOException {
        return createHttpConnection(config,appKey,appSecret,uri,"GET");
    }

    private static HttpURLConnection createHttpConnection(RongCloudConfig config, String appKey, String appSecret, String uri, String method) throws IOException {
        String nonce = String.valueOf(Math.random() * 1000000);
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        StringBuilder toSign = new StringBuilder(appSecret).append(nonce).append(timestamp);
        String sign = CodeUtil.hexSHA1(toSign.toString());
        HttpURLConnection conn = getHttpURLConnection(config, uri);
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod(method);
        conn.setInstanceFollowRedirects(true);
        conn.setConnectTimeout(config.httpConnectTimeout);
        conn.setReadTimeout(config.httpReadTimeout);

        conn.setRequestProperty(APPKEY, appKey);
        conn.setRequestProperty(NONCE, nonce);
        conn.setRequestProperty(TIMESTAMP, timestamp);
        conn.setRequestProperty(SIGNATURE, sign);
        conn.setRequestProperty(USERAGENT, "rc-java-sdk/" + CommonUtil.getSDKVersion());
        conn.setRequestProperty("Connection", config.connectionKeepAlive ? "keep-alive" : "close");
        conn.setRequestProperty("X-Request-ID", UUID.randomUUID().toString().replaceAll("\\-", ""));
        return conn;
    }


    public static HttpURLConnection getHttpURLConnection(RongCloudConfig config, String uri)
            throws IOException {
        URL url = new URL(config.getDomain() + uri);
        return (HttpURLConnection) url.openConnection();
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        int buffer_size = 1024;
        byte[] buffer = new byte[buffer_size];
        int len = 0;
        while ((len = inStream.read(buffer, 0, buffer_size)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }

    public static String returnResult(HttpURLConnection conn, RongCloudConfig config) throws Exception {
        InputStream input = null;
        String result = "";
        try {
            if (conn.getResponseCode() == 200) {
                input = conn.getInputStream();
            } else {
                if (conn.getResponseCode() > 500 && conn.getResponseCode() < 600) {
                    config.errorCounter.incrementAndGet();
                }
                input = conn.getErrorStream();
            }
            result = new String(readInputStream(input), "UTF-8");
            JSONObject object = JSON.parseObject(result);
            object.put("requestId", conn.getRequestProperty("X-Request-ID"));
            result = object.toString();
        } catch (UnknownHostException e) {
            result = getExceptionMessage("request:" + conn.getURL() + " ,x_request_id:" + conn.getRequestProperty("X-Request-ID") + " ,UnknownHostException:" + e.getMessage());
            config.errorCounter.incrementAndGet();
        } catch (SocketTimeoutException e) {
            result = getExceptionMessage("request:" + conn.getURL() + " ,x_request_id:" + conn.getRequestProperty("X-Request-ID") + " ,SocketTimeoutException:" + e.getMessage());
            config.errorCounter.incrementAndGet();
        } catch (IOException e) {
            result = getExceptionMessage("request:" + conn.getURL() + " ,x_request_id:" + conn.getRequestProperty("X-Request-ID") + " ,IOException:" + e.getMessage());
            config.errorCounter.incrementAndGet();
        } catch (Exception e) {
            config.errorCounter.incrementAndGet();
            throw e;
        }
        return result;
    }

    private static String getExceptionMessage(String error) {
        return GsonUtil.toJson(new ResponseResult(400, error), ResponseResult.class);
    }

}
