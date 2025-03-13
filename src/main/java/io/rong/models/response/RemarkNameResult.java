package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * Query Remark Name Result
 */
public class RemarkNameResult extends ResponseResult {

    // Remark Name
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
