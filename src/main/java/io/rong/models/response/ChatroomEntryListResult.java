package io.rong.models.response;

import java.util.List;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * 查询聊天室属性的应答模板
 * 
 * @author RongCloud
 *
 */
public class ChatroomEntryListResult extends Result {

	public List<ChatroomEntryResult> keys;

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatroomEntryListResult.class);
	}
}
