package io.rong.methods.sensitive;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.models.CheckMethod;
import io.rong.models.response.ListWordfilterResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.CommonConstrants;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 *
 *  敏感词服务
 * docs: "http://www.rongcloud.cn/docs/server.html#sensitiveword"
 *
 * @version
 * */
public class Wordfilter {

	private static final String UTF8 = "UTF-8";
	private static final String PATH = "sensitiveword";
	private String appKey;
	private String appSecret;
	private RongCloud rongCloud;

	public RongCloud getRongCloud() {
		return rongCloud;
	}
	public void setRongCloud(RongCloud rongCloud) {
		this.rongCloud = rongCloud;
	}
	public Wordfilter(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}
	
	
	/**
	 * 添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。） 
	 * 
	 * @param  word:敏感词，最长不超过 32 个字符。（必传）
	 *
	 * @return ResponseResult
	 **/
	public ResponseResult add(String word) throws Exception {
		String message = CommonUtil.checkParam("keyword",word,PATH, CheckMethod.ADD);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&word=").append(URLEncoder.encode(word.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/wordfilter/add.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn)), ResponseResult.class);
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
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/wordfilter/list.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (ListWordfilterResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn)), ListWordfilterResult.class);
	}
	
	/**
	 * 移除敏感词方法（从敏感词列表中，移除某一敏感词。） 
	 * 
	 * @param  word:敏感词，最长不超过 32 个字符。（必传）
	 *
	 * @return ResponseResult
	 **/
	public ResponseResult remove(String word) throws Exception {
		if (word == null) {
			throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL,"/wordfilter/delete","Paramer 'word' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&word=").append(URLEncoder.encode(word.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/wordfilter/delete.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.REMOVE,HttpUtil.returnResult(conn)), ResponseResult.class);
	}

	 
}