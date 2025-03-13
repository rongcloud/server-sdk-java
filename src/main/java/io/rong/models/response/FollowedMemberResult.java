package io.rong.models.response;

import io.rong.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Query result for group member aliases
 */
public class FollowedMemberResult extends ResponseResult{

	private String userId;
	private String groupId;
	private List<FollowedMember> members = new ArrayList<>();

	public FollowedMemberResult(Integer code, String msg) {
		super(code, msg);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<FollowedMember> getMembers() {
		return members;
	}

	public void setMembers(List<FollowedMember> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, FollowedMemberResult.class);
	}
}
