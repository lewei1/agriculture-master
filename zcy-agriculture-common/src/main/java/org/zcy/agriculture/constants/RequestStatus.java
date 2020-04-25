package org.zcy.agriculture.constants;

public enum RequestStatus {

	SUCCESS(0, "成功"),
	FAILED(-1, "失败"),
	NOT_LOGIN(-101,"未登录"),
	DUPLICATE_REQUEST(-102,"您的请求过于频繁，请稍后再试..."),
	PARAM_REQUIRED(-2,"参数异常，请检查请求参数！"),
	;
	private Integer status;
	private String message;

	RequestStatus(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public static RequestStatus stateOf(int index) {
		for (RequestStatus requestStatus : values()) {
			if (requestStatus.getStatus() == index) {
				return requestStatus;
			}
		}
		return null;
	}
}

