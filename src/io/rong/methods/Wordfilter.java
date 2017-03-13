package io.rong.methods;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import io.rong.models.*;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import io.rong.util.HostType;

public class Wordfilter {

	private static final String UTF8 = "UTF-8";
	private String appKey;
	private String appSecret;
	
	public Wordfilter(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}
	
	
	/**
	 * 添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。） 
	 * 
	 * @param  word:敏感词，最长不超过 32 个字符。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult add(String word) throws Exception {
		if (word == null) {
			throw new IllegalArgumentException("Paramer 'word' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&word=").append(URLEncoder.encode(word.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/wordfilter/add.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 查询敏感词列表方法 
	 * 
	 *
	 * @return ListWordfilterResult
	 **/
	public ListWordfilterResult getList() throws Exception {
	    StringBuilder sb = new StringBuilder();
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/wordfilter/list.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (ListWordfilterResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), ListWordfilterResult.class);
	}
	
	/**
	 * 移除敏感词方法（从敏感词列表中，移除某一敏感词。） 
	 * 
	 * @param  word:敏感词，最长不超过 32 个字符。（必传）
	 *
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