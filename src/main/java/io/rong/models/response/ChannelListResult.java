package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

import java.util.List;

/**
 * ChannelListResult
 */
public class ChannelListResult extends Result {

	// Channel list
	List<ChannelInfo> channelList;

	public ChannelListResult(Integer code, List<ChannelInfo> channelList , String errorMessage) {
		super(code, errorMessage);
		this.code = code;
		this.channelList = channelList;
		this.errorMessage = errorMessage;
	}

	public List<ChannelInfo> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<ChannelInfo> channelList) {
		this.channelList = channelList;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChannelListResult.class);
	}


	class ChannelInfo {
		String channelId;
		int type;
		String createTime;

		public String getChannelId() {
			return channelId;
		}

		public void setChannelId(String channelId) {
			this.channelId = channelId;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}
	}
}
