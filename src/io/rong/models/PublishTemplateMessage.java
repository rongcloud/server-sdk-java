package io.rong.models;

import java.util.List;
import java.util.Map;

import io.rong.util.GsonUtil;


public class PublishTemplateMessage {

	private String fromUserId;
	private String objectName;
	private String content;
	private String[] toUserId;
	private List<Map<String, String>> values;
	private String[] pushContent;
	private String[] pushData;
	private int verifyBlacklist;
	private int isPersisted = 1;
	private int isCounted = 1;

	public int getVerifyBlacklist() {
		return verifyBlacklist;
	}

	public void setVerifyBlacklist(int verifyBlacklist) {
		this.verifyBlacklist = verifyBlacklist;
	}

	public String[] getPushContent() {
		return pushContent;
	}

	public void setPushContent(String[] pushContent) {
		this.pushContent = pushContent;
	}

	public String[] getPushData() {
		return pushData;
	}

	public void setPushData(String[] pushData) {
		this.pushData = pushData;
	}

	public List<Map<String, String>> getValues() {
		return values;
	}

	public void setValues(List<Map<String, String>> values) {
		this.values = values;
	}

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getToUserId() {
		return toUserId;
	}

	public void setToUserId(String[] toUserId) {
		this.toUserId = toUserId;
	}

	public int getIsPersisted() {
		return isPersisted;
	}

	public void setIsPersisted(int isPersisted) {
		this.isPersisted = isPersisted;
	}

	public int getIsCounted() {
		return isCounted;
	}

	public void setIsCounted(int isCounted) {
		this.isCounted = isCounted;
	}

	public PublishTemplateMessage() {
		super();
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, PublishTemplateMessage.class);
	}
}
