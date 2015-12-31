package io.rong.models;

import io.rong.util.GsonUtil;

public class UserTag {
	private String[] tags;
	private String userId;

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, UserTag.class);
	}
}
