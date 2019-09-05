package io.rong.util;

import org.apache.commons.codec.digest.DigestUtils;
import com.alibaba.fastjson.JSONObject;

import java.net.*;

/**
 * 百度 HttpDNSUtil
 */
public class BaiduHttpDNSUtil {

    public static String uri = "http://180.76.76.200/v3/resolve";
    private static final String UTF8 = "UTF-8";

    /**
     * 通过 百度 HTTPDNS 获取最优的 IP 地址
     *
     * @param account_id 6位数字账户id，如“107080”。开通HTTPDNS服务后为用户分配
     * @param secret     用来生成签名的私钥
     * @param dn         待解析的域名
     * @return ip地址
     * @throws Exception
     */
    public static String getFirstIp(String account_id, String secret, String dn) throws Exception {
        if (account_id == null || secret == null || dn == null ||
                account_id.equals("") || secret.equals("") || dn.equals("")) {
            throw new Exception("account_id、secret and dn is required.");
        }
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000 + 10);
        String sign = DigestUtils.md5Hex(dn + "-" + secret + "-" + timestamp);

        StringBuilder sb = new StringBuilder();
        sb.append("?account_id=").append(URLEncoder.encode(account_id, UTF8));
        sb.append("&dn=").append(URLEncoder.encode(dn, UTF8));
        sb.append("&t=").append(URLEncoder.encode(timestamp, UTF8));
        sb.append("&sign=").append(URLEncoder.encode(sign, UTF8));
        String param = sb.toString();

        URL url = new URL(uri + param);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(30000);
        String result = HttpUtil.returnResult(conn);
        JSONObject btResult = (JSONObject) GsonUtil.fromJson(result, JSONObject.class);
        JSONObject data = btResult.getJSONObject("data");
        if (data == null || !data.containsKey(dn)) {
            throw new Exception("BaiduHttpDNSUtil: " + result);
        } else {
            return data.getJSONObject(dn).getJSONArray("ip").getString(0);
        }
    }

    /**
     * 通过 百度 HTTPDNS 获取最优的 IP 地址
     *
     * @param account_id 6位数字账户id，如“107080”。开通HTTPDNS服务后为用户分配
     * @param secret     用来生成签名的私钥
     * @param hostType   默认使用的域名实体对象
     * @throws Exception
     */
    public static void setHostTypeIp(String account_id, String secret, HostType hostType) throws Exception {
        hostType.setIp(BaiduHttpDNSUtil.getFirstIp(
                account_id, secret, new java.net.URL(hostType.getStrType()).getHost()));
    }
}
