package io.rong.messages;

import io.rong.util.GsonUtil;

public class FileMessage extends BaseMessage {

	/**
	 * 文件名称
	 */
	private String name;

	/**
	 * 文件大小，单位：bytes
	 */
	private Long size;

	/**
	 * 文件地址
	 */
	private String fileUrl;
	/**
	 * 扩展信息，可以放置任意的数据内容，也可以去掉此属性。
	 */
	private String extra;

	/**
	 * 文件类型
	 */
	private transient static final String TYPE = "RC:FileMsg";

	public FileMessage() {
		super();
	}

	public FileMessage(String name, Long size, String fileUrl) {
		super();
		this.name = name;
		this.size = size;
		this.fileUrl = fileUrl;
	}

	public FileMessage(String name, Long size, String fileUrl, String extra) {
		super();
		this.name = name;
		this.size = size;
		this.fileUrl = fileUrl;
		this.extra = extra;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, FileMessage.class);
	}

}
