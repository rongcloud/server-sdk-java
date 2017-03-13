package io.rong.models;

import io.rong.util.GsonUtil;
import java.util.List;

/**
 * groupUserQuery返回结果
 */
public class GroupUserQueryResult {
	// 返回码，200 为正常。
	Integer code;
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
	 * 设置code
	 *
	 */	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	/**
	 * 获取code
	 *
	 * @return Integer
	 */
	public Integer getCode() {
		return code;
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
