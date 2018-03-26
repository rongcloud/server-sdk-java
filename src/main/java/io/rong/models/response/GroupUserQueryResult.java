package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;
import java.util.List;

/**
 * groupUserQuery返回结果
 */
public class GroupUserQueryResult extends Result{

	// 群成员用户Id。
	String id;
	// 群成员列表。
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
	 * 设置id
	 *
	 */	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获取id
	 *
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 获取members
	 *
	 * @return members
	 */
	public List<GroupUser> getMembers() {
		return this.members;
	}
	/**
	 * 设置members
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
