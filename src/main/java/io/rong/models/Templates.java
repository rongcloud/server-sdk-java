package io.rong.models;

import io.rong.util.GsonUtil;
import java.util.Map;
import java.util.List;

/**
 * 模版消息对象。
 * @author RongCloud
 */
public class Templates {
	 /**
	  * 发送人用户 Id。（必传）
	  * */
	String fromUserId;
	/**
	 * 接收用户 Id，提供多个本参数可以实现向多人发送消息，上限为 1000 人。（必传）
	 **/
	String[] toUserId;
	/**
	 *发送消息内容，内容中定义标识通过 values 中设置的标识位内容进行替换，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）
	 * */
	String content;
	/**
	 *
	 */
	List<Map<String, String>> values;
	// 接收用户 Id，提供多个本参数可以实现向多人发送消息，上限为 1000 人。（必传）
	String objectName;
	// 定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息。如果为自定义消息，定义显示的 Push 内容，内容中定义标识通过 values 中设置的标识位内容进行替换。如消息类型为自定义不需要 Push 通知，则对应数组传空值即可。（必传）
	String[] pushContent;
	// 针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。如不需要 Push 功能对应数组传空值即可。（可选）
	String[] pushData;

	String[] pushExt;
	// 是否过滤发送人黑名单列表，0 为不过滤、 1 为过滤，默认为 0 不过滤。（可选）
	Integer verifyBlacklist;

	Integer contentAvailable;

	Long msgRandom;

	/**
	 * 是否为静默消息，默认为 false，设为 true 时终端用户离线情况下不会收到通知提醒（可选）。暂不支持海外数据中心
	 */
	Boolean disablePush;

	/**
	 * 禁止更新会话最后一条消息。 当该参数为 false 时，发送的该条消息都会进入会话列表; 为 true 时，不会更新到会话列表的消息内容。
	 * 注：此参数仅对存储在客户端的消息有效。
	 */
	private Boolean disableUpdateLastMsg;


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
	 * 设置fromUserId
	 *
	 */
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	/**
	 * 获取fromUserId
	 *
	 * @return String
	 */
	public String getFromUserId() {
		return fromUserId;
	}

	/**
	 * 设置toUserId
	 *
	 */
	public void setToUserId(String[] toUserId) {
		this.toUserId = toUserId;
	}

	/**
	 * 获取toUserId
	 *
	 * @return String[]
	 */
	public String[] getToUserId() {
		return toUserId;
	}

	/**
	 * 设置content
	 *
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取content
	 *
	 * @return String
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置values
	 *
	 */
	public void setValues(List<Map<String, String>> values) {
		this.values = values;
	}

	/**
	 * 获取values
	 *
	 * @return List
	 */
	public List<Map<String, String>> getValues() {
		return values;
	}

	/**
	 * 设置objectName
	 *
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * 获取objectName
	 *
	 * @return String
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * 设置pushContent
	 *
	 */
	public void setPushContent(String[] pushContent) {
		this.pushContent = pushContent;
	}

	/**
	 * 获取pushContent
	 *
	 * @return String[]
	 */
	public String[] getPushContent() {
		return pushContent;
	}

	/**
	 * 设置pushData
	 *
	 */
	public void setPushData(String[] pushData) {
		this.pushData = pushData;
	}

	/**
	 * 获取pushData
	 *
	 * @return String[]
	 */
	public String[] getPushData() {
		return pushData;
	}


	/**
	 * 设置pushExt
	 *
	 */
	public void setPushExt(String[] pushExt) {
		this.pushData = pushExt;
	}

	/**
	 * 获取pushExt
	 *
	 * @return String[]
	 */
	public String[] getPushExt() {
		return pushExt;
	}


	/**
	 * 设置verifyBlacklist
	 *
	 */
	public void setVerifyBlacklist(Integer verifyBlacklist) {
		this.verifyBlacklist = verifyBlacklist;
	}

	/**
	 * 获取verifyBlacklist
	 *
	 * @return Integer
	 */
	public Integer getVerifyBlacklist() {
		return verifyBlacklist;
	}

	/**
	 * 获取contentAvailable
	 *
	 * @return Integer
	 */
	public Integer getContentAvailable() {
		return this.contentAvailable;
	}
	/**
	 * 设置contentAvailable
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

	@Override
	public String toString() {
		return GsonUtil.toJson(this, Templates.class);
	}


}
