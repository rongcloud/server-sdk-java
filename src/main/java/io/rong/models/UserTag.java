package io.rong.models;

import io.rong.util.GsonUtil;

/**
 * 用于打标签的对象。
 */
public class UserTag {
	// 用户标签，一个用户最多添加 20 个标签，每个 tags 最大不能超过 40 个字节，标签中不能包含特殊字符。（必传）
	String[] tags;
	// 用户 Id。（必传）
	String userId;
	
	public UserTag(String[] tags, String userId) {
		this.tags = tags;
		this.userId = userId;
	}
	
	/**
	 * 设置tags
	 *
	 */	
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
	/**
	 * 获取tags
	 *
	 * @return String[]
	 */
	public String[] getTags() {
		return tags;
	}
	
	/**
	 * 设置userId
	 *
	 */	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取userId
	 *
	 * @return String
	 */
	public String getUserId() {
		return userId;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, UserTag.class);
	}
}
