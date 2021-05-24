package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.chatroom.ChatroomEntryModel;
import io.rong.util.GsonUtil;

/**
 * 查询聊天室属性的应答模板
 * 
 * @author RongCloud
 *
 */
public class ChatroomEntryResult extends Result {
	public String key;
	public String value;
	public String userId;
	public int autoDelete;
	public String lastSetTime;

	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public int getAutoDelete() {
		return autoDelete;
	}

	public void setAutoDelete(int autoDelete) {
		this.autoDelete = autoDelete;
	}

	public void setAutoDelete(Boolean autoDelete) {
		this.autoDelete = autoDelete ? 1 : 0;
	}


	public String getLastSetTime() {
		return lastSetTime;
	}

	public void setLastSetTime(String lastSetTime) {
		this.lastSetTime = lastSetTime;
	}


	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatroomEntryResult.class);
	}
}
