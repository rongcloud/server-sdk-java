package io.rong.models;

import java.util.List;

public class TagObj {
	private List<String> tag;// 最多20个
	private List<String> userid;// 最多1000个
	private boolean is_to_all;// true为全部，忽略上面的tag

	public List<String> getTag() {
		return tag;
	}

	public void setTag(List<String> tag) {
		this.tag = tag;
	}

	public List<String> getUserid() {
		return userid;
	}

	public void setUserid(List<String> userid) {
		this.userid = userid;
	}

	public boolean isIs_to_all() {
		return is_to_all;
	}

	public void setIs_to_all(boolean is_to_all) {
		this.is_to_all = is_to_all;
	}

	public TagObj() {
		super();
	}
}
