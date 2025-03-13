package io.rong.methods.sensitive;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.response.BatchAddSensitiveWordResult;
import io.rong.models.response.ListWordfilterResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.sensitiveword.AddSensitiveWordsModel;
import io.rong.models.sensitiveword.SensitiveWordModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 *
 * Sensitive Word Service
 * docs: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
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
     * Add Sensitive Word Method (After setting sensitive words, users in the App will not receive messages containing sensitive words. By default, up to 50 sensitive words can be set.)
     * @param sensitiveword
     * @return
     * @throws Exception
     */

	public ResponseResult add(SensitiveWordModel sensitiveword) throws Exception {

		String errMsg = CommonUtil.checkFiled(sensitiveword,PATH,CheckMethod.ADD);
		if(null != errMsg){
			return (ResponseResult)GsonUtil.fromJson(errMsg,ResponseResult.class);
		}

	    StringBuilder sb = new StringBuilder();
	    sb.append("&word=").append(URLEncoder.encode(sensitiveword.getKeyword().toString(), UTF8));

	    if(0 == sensitiveword.getType()){
	    	if(null == sensitiveword.getReplace()){
	    		return new ResponseResult(20005,"The replace parameter is required");
			}
			sb.append("&replaceWord=").append(URLEncoder.encode(sensitiveword.getReplace().toString(), UTF8));
		}

		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/sensitiveword/add.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
	}


    /**
     * Adds a sensitive word. (After setting a sensitive word, users in the App will not receive messages containing the sensitive word. By default, up to 50 sensitive words can be set.)
     *
     * @param sensitiveWords: Sensitive words
     * @return ResponseResult
     **/
    public BatchAddSensitiveWordResult batchAdd(AddSensitiveWordsModel sensitiveWords) throws Exception {
        if (sensitiveWords == null || sensitiveWords.getWords() == null || sensitiveWords.getWords().isEmpty()) {
            return new BatchAddSensitiveWordResult(20005, "The sensitiveWords parameter is required");
        }

        if (sensitiveWords.getWords().size() > 50) {
            return new BatchAddSensitiveWordResult(20005, "The number of sensitive words exceeds 50");
        }

        for (AddSensitiveWordsModel.SensitiveWord word : sensitiveWords.getWords()) {
            if (StringUtils.isEmpty(word.getWord())) {
                return new BatchAddSensitiveWordResult(20005, "The word parameter is required");
            }
            if (word.getWord().length() > 32) {

                return new BatchAddSensitiveWordResult(20005, "The length of the 'word' parameter exceeds 32");
            }
            if (word.getReplaceWord() != null && word.getReplaceWord().length() > 32) {
                return new BatchAddSensitiveWordResult(20005, "The length of the 'replaceWord' parameter exceeds 32");
            }
        }

		String body = GsonUtil.toJson(sensitiveWords);

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/sensitiveword/batch/add.json", "application/json");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

		String responseByCode = CommonUtil.getResponseByCode(PATH, CheckMethod.BATCH_ADD, HttpUtil.returnResult(conn, rongCloud.getConfig()));
		return (BatchAddSensitiveWordResult) GsonUtil.fromJson(responseByCode, BatchAddSensitiveWordResult.class);
	}

    /**
     * Method to query the list of sensitive words
     *
     * @param  type: The type of sensitive words to query. 0 for querying replacement sensitive words, 1 for querying blocked sensitive words, and 2 for querying all sensitive words. Default is 1. (Optional)
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

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/sensitiveword/list.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

	    return (ListWordfilterResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn, rongCloud.getConfig())), ListWordfilterResult.class);
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

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/sensitiveword/delete.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
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
			sb.append("&words=").append(URLEncoder.encode(word.toString(), UTF8));
		}
		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/sensitiveword/batch/delete.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

		return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.BATCH_DELETE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
	}

}