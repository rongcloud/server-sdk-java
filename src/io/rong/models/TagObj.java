package io.rong.models;

import io.rong.util.GsonUtil;

/**
 * 用于Push中的 标签。
 */
public class TagObj {
	// 标签。（最多20个）
	String[] tag;
	// 如果填 userId 给 userId 发如果没有给 tag 发。（最多1000个）
	String[] userid;
	// true为全部，忽略上面的tag、userId。（必传）
	Boolean is_to_all;
	
	public TagObj(String[] tag, String[] userid, Boolean is_to_all) {
		this.tag = tag;
		this.userid = userid;
		this.is_to_all = is_to_all;
	}
	
	/**
	 * 设置tag
	 *
	 */	
	public void setTag(String[] tag) {
		this.tag = tag;
	}
	
	/**
	 * 获取tag
	 *
	 * @return String[]
	 */
	public String[] getTag() {
		return tag;
	}
	
	/**
	 * 设置userid
	 *
	 */	
	public void setUserid(String[] userid) {
		this.userid = userid;
	}
	
	/**
	 * 获取userid
	 *
	 * @return String[]
	 */
	public String[] getUserid() {
		return userid;
	}
	
	/**
	 * 设置is_to_all
	 *
	 */	
	public void setIs_to_all(Boolean is_to_all) {
		this.is_to_all = is_to_all;
	}
	
	/**
	 * 获取is_to_all
	 *
	 * @return Boolean
	 */
	public Boolean getIs_to_all() {
		return is_to_all;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, TagObj.class);
	}
}
