package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.util.GsonUtil;
import java.util.List;

/**
 * The result of listing blocked chatroom users.
 */
public class ListBlockChatroomUserResult extends Result {
    /**
     * The list of banned users.
     */
    List<ChatroomMember> members;

    public ListBlockChatroomUserResult(Integer code, String msg, List<ChatroomMember> members) {
        super(code, msg);
        this.members = members;
    }

    public ListBlockChatroomUserResult(List<ChatroomMember> members) {
        this.members = members;
    }

    /**
     * Gets the list of banned users.
     *
     * @return List
     */
    public List<ChatroomMember> getMembers() {
        return this.members;
    }

    /**
     * Sets the list of banned users.
     */
    public void setMembers(List<ChatroomMember> members) {
        this.members = members;
    }

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ListBlockChatroomUserResult.class);
	}
}
