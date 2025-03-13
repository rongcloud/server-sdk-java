package io.rong.messages;

import io.rong.util.GsonUtil;

/**
 *
 * General command notification message. This type of message does not have push notification. 
 * It differs from the general command notification message in that it is not stored or counted.
 *
 */
public class CmdMsgMessage extends BaseMessage {
	private String name = "";
	private String data = "";
	private transient static final String TYPE = "RC:CmdMsg";

	public CmdMsgMessage(String name, String data) {
		this.name = name;
		this.data = data;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	/**
	 * Gets the command name, which can be user-defined.
	 *
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Sets the command name, which can be user-defined.
	 *
	 *
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the content of the command.
	 *
	 * @return String
	 */
	public String getData() {
		return data;
	}

    /**
     * @param data The content of the command to be set
     */
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, CmdMsgMessage.class);
	}
}