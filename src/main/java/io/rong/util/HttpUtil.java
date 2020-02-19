package io.rong.util;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * http 公共服务
 *
 * @author RongCloud
 */
public class HttpUtil {

    private static final String APPKEY = "App-Key";
    private static final String USERAGENT = "User-Agent";
    private static final String NONCE = "Nonce";
    private static final String TIMESTAMP = "Timestamp";
    private static final String SIGNATURE = "Signature";
    public static AtomicInteger timeoutNum = new AtomicInteger();
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

    // 设置body体
    public static void setBodyParameter(String str, HttpURLConnection conn) throws IOException {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(conn.getOutputStream());
            out.write(str.getBytes("utf-8"));
            out.flush();
        } catch (UnknownHostException e) {
            timeoutNum.incrementAndGet();
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            timeoutNum.incrementAndGet();
            e.printStackTrace();
        } catch (IOException e) {
            timeoutNum.incrementAndGet();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != out) {
                out.close();
            }
        }

    }

    public static HttpURLConnection CreatePostHttpConnection(HostType hostType, String appKey, String appSecret,
        String uri) throws MalformedURLException, IOException, ProtocolException {
        return CreatePostHttpConnection(hostType, appKey, appSecret, uri, "application/x-www-form-urlencoded");
    }

    public static HttpURLConnection CreatePostHttpConnection(HostType hostType, String appKey, String appSecret,
        String uri,
        String contentType) throws MalformedURLException, IOException, ProtocolException {
        String nonce = String.valueOf(Math.random() * 1000000);
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        StringBuilder toSign = new StringBuilder(appSecret).append(nonce).append(timestamp);
        String sign = CodeUtil.hexSHA1(toSign.toString());
        HttpURLConnection conn = getHttpURLConnection(hostType, uri);
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setInstanceFollowRedirects(true);
        conn.setConnectTimeout(hostType.getConnectTimeout());
        conn.setReadTimeout(hostType.getReadTimeout());

        conn.setRequestProperty(APPKEY, appKey);
        conn.setRequestProperty(NONCE, nonce);
        conn.setRequestProperty(TIMESTAMP, timestamp);
        conn.setRequestProperty(SIGNATURE, sign);
        conn.setRequestProperty(USERAGENT, "rc-java-sdk/" + CommonUtil.getSDKVersion());
        conn.setRequestProperty("Content-Type", contentType);

        return conn;
    }

    public static HttpURLConnection getHttpURLConnection(HostType hostType, String uri)
        throws IOException {
        //是否配置了 IP 如果配置了，则走 Header + Host 模式
        if (StringUtils.isEmpty(hostType.getIp())) {
            uri = hostType.getStrType() + uri;
            URL url = new URL(uri);
            return (HttpURLConnection) url.openConnection();
        } else {
            System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
            uri = "http://" + hostType.getIp() + uri;
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Host", new java.net.URL(hostType.getStrType()).getHost());
            return conn;
        }
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }

    public static String returnResult(HttpURLConnection conn) throws Exception {
        InputStream input = null;
        String result = "";
        try {
            if (conn.getResponseCode() == 200) {
                input = conn.getInputStream();
            } else {
                if (conn.getResponseCode() == 502) {
                    timeoutNum.incrementAndGet();
                }
                input = conn.getErrorStream();
            }
            result = new String(readInputStream(input), "UTF-8");
        } catch (UnknownHostException e) {
            timeoutNum.incrementAndGet();
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            timeoutNum.incrementAndGet();
            e.printStackTrace();
        } catch (IOException e) {
            timeoutNum.incrementAndGet();
            e.printStackTrace();
        } catch (Exception e) {
            timeoutNum.incrementAndGet();
            e.printStackTrace();
            throw e;
        }
        return result;
    }
}
