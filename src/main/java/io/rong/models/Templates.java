package io.rong.models;

import io.rong.util.GsonUtil;
import java.util.Map;
import java.util.List;

/**
 * Template message object.
 * @author RongCloud
 */
public class Templates {
	 /**
	  * Sender's user ID. (Required)
	  * */
	String fromUserId;
	/**
	 * Recipient user IDs. Providing multiple IDs allows sending messages to multiple users, with a limit of 1000 users. (Required)
	 **/
	String[] toUserId;
	/**
	 * Message content. The content defines placeholders that are replaced by values set in the `values` parameter. Refer to the RongCloud message type table for examples. If the `objectName` is a custom message type, this parameter can be customized. (Required)
	 * */
	String content;
	/**
	 *
	 */
	List<Map<String, String>> values;
	// Recipient user IDs. Providing multiple IDs allows sending messages to multiple users, with a limit of 1000 users. (Required)
	String objectName;
	// Defines the Push content to be displayed. If the `objectName` is a built-in RongCloud message type, the user will definitely receive a Push notification after sending. For custom messages, define the Push content to be displayed, with placeholders replaced by values set in the `values` parameter. If the message type is custom and no Push notification is needed, pass an empty array. (Required)
	String[] pushContent;
	// For iOS platforms, this is attached to the Push notification payload. For Android clients, the corresponding field is `pushData`. If no Push functionality is needed, pass an empty array. (Optional)
	String[] pushData;

	String[] pushExt;
	// Whether to filter the sender's blocklist. 0 means no filtering, 1 means filtering. Default is 0 (no filtering). (Optional)
	Integer verifyBlacklist;

	Integer contentAvailable;

	Long msgRandom;

	/**
	 * Whether the message is silent. Default is false. When set to true, terminal users will not receive notification reminders when offline. (Optional). Not supported in global data centers.
	 */
	Boolean disablePush;

	/**
	 * Prohibit updating the last message in the conversation. When this parameter is false, the sent message will appear in the conversation list; when true, it will not update the message content in the conversation list.
	 * Note: This parameter only affects messages stored on the client side.
	 */
	private Boolean disableUpdateLastMsg;

	/**
	 * Whether the message need readReceipt; 0 means not needed, 1 means need. Default is 0.
	 */
	Integer needReadReceipt;

	public Templates() {
	}

	public Templates(String fromUserId, String[] toUserId, String content, List<Map<String, String>> values, String objectName, String[] pushContent, String[] pushData, Integer verifyBlacklist) {
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.content = content;
		this.values = values;
		this.objectName = objectName;
		this.pushContent = pushContent;
		this.pushData = pushData;
		this.verifyBlacklist = verifyBlacklist;
	}
	public Templates(String fromUserId, String[] toUserId, String content, List<Map<String, String>> values, String objectName, String[] pushContent, String[] pushData, String[] pushExt, Integer verifyBlacklist) {
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.content = content;
		this.values = values;
		this.objectName = objectName;
		this.pushContent = pushContent;
		this.pushData = pushData;
		this.pushExt = pushExt;
		this.verifyBlacklist = verifyBlacklist;
	}

	/**
	 * Sets the fromUserId
	 *
	 */
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	/**
	 * Gets the fromUserId
	 *
	 * @return String
	 */
	public String getFromUserId() {
		return fromUserId;
	}

	/**
	 * Sets the toUserId
	 *
	 */
	public void setToUserId(String[] toUserId) {
		this.toUserId = toUserId;
	}

	/**
	 * Retrieves the toUserId
	 *
	 * @return String[]
	 */
	public String[] getToUserId() {
		return toUserId;
	}

	/**
	 * Sets the content
	 *
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Retrieves the content
	 *
	 * @return String
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the values
	 *
	 */
	public void setValues(List<Map<String, String>> values) {
		this.values = values;
	}

	/**
	 * Retrieves the values
	 *
	 * @return List
	 */
	public List<Map<String, String>> getValues() {
	    return values;
	}

	/**
	 * Sets the objectName
	 *
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * Retrieves the objectName
	 *
	 * @return String
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * Sets the pushContent
	 *
	 */
	public void setPushContent(String[] pushContent) {
		this.pushContent = pushContent;
	}

	/**
	 * Retrieves the pushContent
	 *
	 * @return String[]
	 */
	public String[] getPushContent() {
		return pushContent;
	}

	/**
	 * Sets the pushData
	 *
	 */
	public void setPushData(String[] pushData) {
		this.pushData = pushData;
	}

	/**
	 * Retrieves the pushData
	 *
	 * @return String[]
	 */
	public String[] getPushData() {
		return pushData;
	}


	/**
	 * Set pushExt
	 *
	 */
	public void setPushExt(String[] pushExt) {
		this.pushData = pushExt;
	}

	/**
	 * Get pushExt
	 *
	 * @return String[]
	 */
	public String[] getPushExt() {
		return pushExt;
	}


	/**
	 * Set verifyBlacklist
	 *
	 */
	public void setVerifyBlacklist(Integer verifyBlacklist) {
		this.verifyBlacklist = verifyBlacklist;
	}

	/**
	 * Get verifyBlacklist
	 *
	 * @return Integer
	 */
	public Integer getVerifyBlacklist() {
		return verifyBlacklist;
	}

	/**
	 * Get contentAvailable
	 *
	 * @return Integer
	 */
	public Integer getContentAvailable() {
		return this.contentAvailable;
	}
	/**
	 * Set contentAvailable
	 *
	 */
	public void setContentAvailable(Integer contentAvailable) {
		this.contentAvailable = contentAvailable;
	}

	public Boolean getDisablePush() {   return disablePush;  }

	public void setDisablePush(Boolean disablePush) {
		this.disablePush = disablePush;
	}

	public Long getMsgRandom() {
		return msgRandom;
	}

	public void setMsgRandom(Long msgRandom) {
		this.msgRandom = msgRandom;
	}

	public Boolean getDisableUpdateLastMsg() {
		return disableUpdateLastMsg;
	}

	public Templates setDisableUpdateLastMsg(Boolean disableUpdateLastMsg) {
		this.disableUpdateLastMsg = disableUpdateLastMsg;
		return this;
	}

	public Integer getNeedReadReceipt() {
		return needReadReceipt;
	}

	public void setNeedReadReceipt(Integer needReadReceipt) {
		this.needReadReceipt = needReadReceipt;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, Templates.class);
	}


}
