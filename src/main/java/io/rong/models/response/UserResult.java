package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * getToken 返回结果
 */
public class UserResult extends Result{
	// 用户名称。
	String userName;
	// 用户头像地址.
	String userPortrait;
	// 用户创建时间
	String createTime;

	public UserResult(String userName, String userPortrait, String createTime, String errorMessage) {
		this.userName = userName;
		this.userPortrait = userPortrait;
		this.createTime = createTime;
		this.errorMessage = errorMessage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPortrait() {
		return userPortrait;
	}

	public void setUserPortrait(String userPortrait) {
		this.userPortrait = userPortrait;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, UserResult.class);
	}
}
