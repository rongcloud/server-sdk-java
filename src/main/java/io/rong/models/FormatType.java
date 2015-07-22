package io.rong.models;

//请求格式
public enum FormatType {
	json("json", 0), xml("xml", 1);

	private String name;
	private int index;
	
	public int getIndex(){
		return index;
	}

	private FormatType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
