package org.zcy.agriculture.constants;

public enum VerifyTypeEnum {
	/**
	 * 注册
	 */
	REGISTER("register"),
	/**
	 * 登录
	 */
	LOGIN("login"),
	/**
	 * 修改密码
	 */
	UPDATEPWD("updatepwd"),
	/**
	 * 找回密码
	 */
	FORGETPWD("forgetpwd"),
	/**
	 * 修改账号
	 */
	UPDATE_ACCOUNT("updateaccount"),
	/**
	 * 解绑手机号
	 */
	UNTIED_PHONE("untiedPhone"),
	/**
	 * 重置手机号
	 */
	RESET_PHONE("untiedPhone")

	;
	/**
	 * name
	 */
	private String name;

	/**
	 *
	 * @param name
	 */
	VerifyTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
