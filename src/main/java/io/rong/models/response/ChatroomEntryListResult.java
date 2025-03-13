package io.rong.models.response;

import java.util.List;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * Response template for querying chatroom attributes
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
