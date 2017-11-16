package io.rong.methods;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import io.rong.RongCloud;
import io.rong.models.*;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import io.rong.util.HostType;

public class Sensitiveword {

	private static final String UTF8 = "UTF-8";
	private String appKey;
	private String appSecret;
	private RongCloud rongCloud;

	public RongCloud getRongCloud() {
		return rongCloud;
	}

	public void setRongCloud(RongCloud rongCloud) {
		this.rongCloud = rongCloud;
	}

	public Sensitiveword(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}
	
	
	/**
	 * 添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。） 
	 * 
	 * @param  word:敏感词，最长不超过 32 个字符。（必传）
	 * @param  replaceWord:需要替换的敏感词，最长不超过 32 个字符，（非必传）。如未设置替换的敏感词，当消息中含有敏感词时，消息将被屏蔽，用户不会收到消息。如设置了替换敏感词，当消息中含有敏感词时，将被替换为指定的字符进行发送。敏感词替换目前只支持单聊、群聊、聊天室会话。
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult add(String word, String replaceWord) throws Exception {
		if (word == null) {
			throw new IllegalArgumentException("Paramer 'word' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&word=").append(URLEncoder.encode(word.toString(), UTF8));
	    
	    if (replaceWord != null) {
	    	sb.append("&replaceWord=").append(URLEncoder.encode(replaceWord.toString(), UTF8));
	    }
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
		
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/wordfilter/add.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 查询敏感词列表方法 
	 * 
	 * @param  type:查询敏感词的类型，0 为查询替换敏感词，1 为查询屏蔽敏感词，2 为查询全部敏感词。默认为 1。（非必传）
	 *
	 * @return ListWordfilterResult
	 **/
	public ListWordfilterResult getList(Integer type) throws Exception {
	    StringBuilder sb = new StringBuilder();
	    
	    if (type != null) {
	    	sb.append("&type=").append(URLEncoder.encode(type.toString(), UTF8));
	    }
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/sensitiveword/list.json", "application/x-www-form-urlencoded");
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
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/sensitiveword/delete.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}

	 
}