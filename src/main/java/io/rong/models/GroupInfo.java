package io.rong.models;

//群信息
public class GroupInfo {

	private String id;
	private String name;

	public GroupInfo(String id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("{\"id\":\"%s\",\"name\":\"%s\"}", id, name);
	}

}
