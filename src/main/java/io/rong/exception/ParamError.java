package io.rong.exception;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("error")
public class ParamError extends Error {
	public ParamError(int errorCode, String apiURL, String errorMessage) {
		super(errorCode, errorCode, apiURL, errorMessage);
	}

	public ParamError(int errorCode, int httpCode, String apiURL,
			String errorMessage) {
		super(errorCode, httpCode, apiURL, errorMessage);
	}

	public ParamError(String apiURL) {
		this(1002, 400, apiURL, "缺少参数，请检查。");
	}

	public ParamError(String apiURL, String message) {
		this(1002, 400, apiURL, message);
	}
}
