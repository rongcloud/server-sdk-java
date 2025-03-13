package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.util.GsonUtil;

import java.util.List;

/**
 * The result of chatroom user query.
 */
public class ChatroomUserQueryResult extends Result {
    /**
     * The total number of users in the chatroom.
     */
    Integer total;
    /**
     * The list of chatroom members.
     */
    List<ChatroomMember> members;

    public ChatroomUserQueryResult(Integer code, String msg, Integer total, List<ChatroomMember> members) {
        super(code, msg);
        this.total = total;
        this.members = members;
    }

    public ChatroomUserQueryResult(Integer total, List<ChatroomMember> members) {
        this.total = total;
        this.members = members;
    }

    /**
     * Sets the total number of users.
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * Gets the total number of users.
     *
     * @return Integer
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * Get members
     *
     * @return List
     */
    public List<ChatroomMember> getMembers() {
        return this.members;
    }

    /**
     * Set members
     *
     */
    public void setMembers(List<ChatroomMember> members) {
        this.members = members;
    }

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatroomUserQueryResult.class);
	}
}
