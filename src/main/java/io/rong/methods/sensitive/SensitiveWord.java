package io.rong.methods.sensitive;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.response.ListWordfilterResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.sensitiveword.SensitiveWordModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 *
 * 敏感词服务
 * docs: "http://www.rongcloud.cn/docs/server.html#sensitiveword"
 *
 * @version
 * */
public class SensitiveWord {

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
	public SensitiveWord(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}
	
	
	/**
	 * 添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。） 
	 * 
	 * @param  sensitiveword:敏感词
	 * @return ResponseResult
	 **/
	public ResponseResult add(SensitiveWordModel sensitiveword) throws Exception {

		String errMsg = CommonUtil.checkFiled(sensitiveword,PATH,CheckMethod.ADD);
		if(null != errMsg){
			return (ResponseResult)GsonUtil.fromJson(errMsg,ResponseResult.class);
		}

	    StringBuilder sb = new StringBuilder();
	    sb.append("&word=").append(URLEncoder.encode(sensitiveword.getKeyword().toString(), UTF8));

	    if(0 == sensitiveword.getType()){
	    	if(null == sensitiveword.getReplace()){
	    		return new ResponseResult(20005,"replace参数为必传项");
			}
			sb.append("&replaceWord=").append(URLEncoder.encode(sensitiveword.getReplace().toString(), UTF8));
		}

		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/sensitiveword/add.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn)), ResponseResult.class);
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
		String message = CommonUtil.checkParam("keyword",word,PATH,CheckMethod.REMOVE);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
		}
	    StringBuilder sb = new StringBuilder();
	    sb.append("&word=").append(URLEncoder.encode(word.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/sensitiveword/delete.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn)), ResponseResult.class);
	}
	/**
	 * 批量移除敏感词方法（从敏感词列表中，移除某一敏感词。）
	 *
	 * @param  words:敏感词数组，一次最多移除 50 个敏感词（必传）
	 *
	 * @return ResponseResult
	 **/
	public ResponseResult batchDelete(String[] words) throws Exception {
		String message = CommonUtil.checkParam("keyword",words,PATH,CheckMethod.BATCH_DELETE);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
		}
		StringBuilder sb = new StringBuilder();
		for(String word : words){
			sb.append("&word=").append(URLEncoder.encode(word.toString(), UTF8));
		}
		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/sensitiveword/batch/delete.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);

		return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.BATCH_DELETE,HttpUtil.returnResult(conn)), ResponseResult.class);
	}
	 
}