package io.rong.methods;

import io.rong.models.CodeSuccessResult;
import io.rong.models.ListSensitiveWordFilterResult;
import io.rong.models.ListWordfilterResult;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class SensitiveWordFilter {
    private static final String UTF8 = "UTF-8";
    private String appKey;
    private String appSecret;

    public SensitiveWordFilter(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    /**
     * 添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。）
     *
     * @param word 敏感词，最长不超过32 个字符。（必传）
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult add(String word) throws Exception {
        return add(word, "json");
    }

    /**
     * 添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。）
     *
     * @param word   敏感词，最长不超过32 个字符。可以包含需要替换的敏感词，最长不超过32个字符，如 word=money&relaceWord=****（必传）
     * @param format 返回格式:json
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult add(String word, String format) throws Exception {
        if (word == null) {
            throw new IllegalArgumentException("Paramer 'word' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&word=").append(URLEncoder.encode(word.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        String uri = "/sensitiveword/add." + format;
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, uri, "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
    }

    /**
     * 查询敏感词列表方法
     *
     * @return ListWordfilterResult
     **/
    public ListSensitiveWordFilterResult getList() throws Exception {
        return getList("1", "json");
    }

    /**
     * 查询敏感词列表方法
     *
     * @param type   查询敏感词的类型，0 为查询替换敏感词，1 为查询屏蔽敏感词，2 为查询全部敏感词。默认为 1。（非必传）
     * @param format 表示返回格式，可以为 json 或 xml。默认为 json。
     * @return ListWordfilterResult
     **/
    public ListSensitiveWordFilterResult getList(String type, String format) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("&type=").append(URLEncoder.encode(type.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        String uri = "/sensitiveword/list." + format;
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, uri, "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ListSensitiveWordFilterResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), ListSensitiveWordFilterResult.class);
    }

    /**
     * 移除敏感词方法（从敏感词列表中，移除某一敏感词。）
     *
     * @param word:敏感词，最长不超过 32 个字符。（必传）
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult delete(String word) throws Exception {
        if (word == null) {
            throw new IllegalArgumentException("Paramer 'word' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&word=").append(URLEncoder.encode(word.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/wordfilter/delete.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
    }
}
