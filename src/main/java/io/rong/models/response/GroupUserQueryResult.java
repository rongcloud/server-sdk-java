package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;
import java.util.List;

/**
 * Group user query result.
 */
public class GroupUserQueryResult extends Result{

	// The user ID of the group member.
	String id;
	// The list of group members.
	List<GroupUser> members;

	public GroupUserQueryResult(Integer code, String msg, String id, List<GroupUser> members) {
		super(code, msg);
		this.id = id;
		this.members = members;
	}

	public GroupUserQueryResult(String id, List<GroupUser> members) {
		this.id = id;
		this.members = members;
	}

	/**
	 * Set the ID.
	 *
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get the ID.
	 *
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get the members.
	 *
	 * @return members
	 */
	public List<GroupUser> getMembers() {
		return this.members;
	}
	/**
	 * Set members
	 *
	 */
	public void setMembers(List<GroupUser> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, GroupUserQueryResult.class);
	}
}
