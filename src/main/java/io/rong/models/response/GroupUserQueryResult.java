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
	List<GroupUser> users;
	
	public GroupUserQueryResult(Integer code, String id, List<GroupUser> users) {
		this.code = code;
		this.id = id;
		this.users = users;
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
	 * 设置users
	 *
	 */	
	public void setUsers(List<GroupUser> users) {
		this.users = users;
	}
	
	/**
	 * 获取users
	 *
	 * @return List<GroupUser>
	 */
	public List<GroupUser> getUsers() {
		return users;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, GroupUserQueryResult.class);
	}
}
