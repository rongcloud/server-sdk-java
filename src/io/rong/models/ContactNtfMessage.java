package io.rong.models;

//添加联系人消息
public class ContactNtfMessage extends Message {

	private String operation;
	private String sourceUserId;
	private String targetUserId;
	private String extra;
	private String message;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(String sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public ContactNtfMessage(String operation, String sourceUserId,
			String targetUserId, String message) {
		this.type = "RC:ContactNtf";
		this.operation = operation;
		this.sourceUserId = sourceUserId;
		this.targetUserId = targetUserId;
		this.message = message;
	}

	public ContactNtfMessage(String operation, String sourceUserId,
			String targetUserId, String message, String extra) {
		this(operation, sourceUserId, targetUserId, message);
		this.extra = extra;
	}

	@Override
	public String toString() {
		return String
				.format("{\"operation\":\"%s\",\"sourceUserId\":\"%s\",\"targetUserId\":\"%s\",\"message\":\"%s\",\"extra\":\"%s\"}",
						operation, sourceUserId, targetUserId, message, extra);
	}
}
