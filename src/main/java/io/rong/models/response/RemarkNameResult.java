package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * 查询备注名返回结果
 */
public class RemarkNameResult extends ResponseResult{

	// 备注名
	String remarkName;

	public RemarkNameResult(Integer code, String msg) {
		super(code, msg);
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, RemarkNameResult.class);
	}
}
