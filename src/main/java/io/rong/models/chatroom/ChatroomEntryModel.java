package io.rong.models.chatroom;

import io.rong.util.GsonUtil;

/**
 * 聊天室属性设置的请求 Request Model
 * 
 * 1. 设置属性 2. 删除属性 3. 获取属性
 * 
 * @author Ronglcoud
 *
 */
public class ChatroomEntryModel {

	/**
	 * 聊天室 Id
	 */
	public String chatroomId;

	/**
	 * 操作用户 Id。通过 Server API 非聊天室中用户可以进行设置。
	 */
	public String userId;

	/**
	 * 聊天室属性名称，Key 支持大小写英文字母、数字、部分特殊符号 + = - _ 的组合方式，大小写敏感。最大长度 128 字符
	 */
	public String key;

	/**
	 * 查询聊天室属性时传的参数 key 的集合, 上限最多 100 个，为空是获取全部的 key 值
	 */
	public String[] keys;

	/**
	 * 聊天室属性对应的值，最大长度 4096 个字符
	 */
	public String value;

	/**
	 * 非必填
	 * 
	 * 用户退出聊天室后，是否删除此 Key 值。true:删除；false不删除此 Key，默认 false
	 */
	public boolean autoDelete = false;

	/**
	 * 非必填
	 * 
	 * 通知消息类型，设置属性后是否发送通知消息，如需要发送则设置为 RC:chrmKVNotiMsg
	 * 或其他自定义消息，为空或不传时不向聊天室发送通知消息，默认为不发送。
	 */
	public String objectName;

	/**
	 * 非必填
	 * 
	 * 通知消息内容，JSON 结构，当 objectName 为 RC:chrmKVNotiMsg 时，content 必须包含 type、key、value
	 * 属性，详细查看 RC:chrmKVNotiMsg 结构说明。
	 */
	public String content;

	public String getChatroomId() {
		return chatroomId;
	}

	public ChatroomEntryModel setChatroomId(String chatroomId) {
		this.chatroomId = chatroomId;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public ChatroomEntryModel setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getKey() {
		return key;
	}

	public ChatroomEntryModel setKey(String key) {
		this.key = key;
		return this;
	}

	public String getValue() {
		return value;
	}

	public ChatroomEntryModel setValue(String value) {
		this.value = value;
		return this;
	}

	public boolean isAutoDelete() {
		return autoDelete;
	}

	public ChatroomEntryModel setAutoDelete(boolean autoDelete) {
		this.autoDelete = autoDelete;
		return this;
	}

	public String getObjectName() {
		return objectName;
	}

	public ChatroomEntryModel setObjectName(String objectName) {
		this.objectName = objectName;
		return this;
	}

	public String getContent() {
		return content;
	}

	public ChatroomEntryModel setContent(String content) {
		this.content = content;
		return this;
	}

	public String[] getKeys() {
		return keys;
	}

	public ChatroomEntryModel setKeys(String[] keys) {
		this.keys = keys;
		return this;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatroomEntryModel.class);
	}
}
