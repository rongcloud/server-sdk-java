package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;
import java.util.List;

/**
 * listBlockChatroomUser返回结果
 */
public class ListBlockChatroomUserResult extends Result{
	/**
	 * 被封禁用户列表
	 *
	 */
	List<BlockChatRoomUser> members;

	public ListBlockChatroomUserResult(Integer code, String msg, List<BlockChatRoomUser> members) {
		super(code, msg);
		this.members = members;
	}

	public ListBlockChatroomUserResult(List<BlockChatRoomUser> members) {
		this.members = members;
	}
	/**
	 * 获取members
	 *
	 * @return List<BlockChatRoomUser>
	 */
	public List<BlockChatRoomUser> getMembers() {
		return this.members;
	}
	/**
	 * 设置members
	 *
	 */
	public void setMembers(List<BlockChatRoomUser> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ListBlockChatroomUserResult.class);
	}
}
