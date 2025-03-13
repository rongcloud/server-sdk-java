package io.rong.messages;

import io.rong.util.GsonUtil;

public class FileMessage extends BaseMessage {

	/**
	 * File name
	 */
	private String name;

	/**
	 * File size in bytes
	 */
	private Long size;

	/**
	 * File URL
	 */
	private String fileUrl;
	/**
	 * Extended information, can store any data content, or this attribute can be removed.
	 */
	private String extra;

	/**
	 * File type
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
