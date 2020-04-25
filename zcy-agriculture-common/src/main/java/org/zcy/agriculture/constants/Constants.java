package org.zcy.agriculture.constants;

/**
 * 通用常量信息
 * 
 * @author numberone
 */
public class Constants {
	/**
	 * UTF-8 字符集
	 */
	public static final String UTF8 = "UTF-8";

	/**
	 * 通用成功标识
	 */
	public static final String SUCCESS = "0";

	/**
	 * 通用失败标识
	 */
	public static final String FAIL = "1";

	/**
	 * 登录成功
	 */
	public static final String LOGIN_SUCCESS = "Success";

	/**
	 * 注销
	 */
	public static final String LOGOUT = "Logout";

	/**
	 * 登录失败
	 */
	public static final String LOGIN_FAIL = "Error";

	/**
	 * 自动去除表前缀
	 */
	public static String AUTO_REOMVE_PRE = "true";

	/**
	 * 当前记录起始索引
	 */
	public static String PAGE_NUM = "pageNum";

	/**
	 * 每页显示记录数
	 */
	public static String PAGE_SIZE = "pageSize";

	/**
	 * 排序列
	 */
	public static String ORDER_BY_COLUMN = "orderByColumn";

	/**
	 * 排序的方向 "desc" 或者 "asc".
	 */
	public static String IS_ASC = "isAsc";

	/**
	 * 登录存用户信息
	 */
	public static String user = "user";

	/**
	 * 商户用户-用户名
	 */
	public static final String LOGIN_USERNAME = "LOGIN_USERNAME";
	/**
	 * 商户用户-密码
	 */
	public static final String LOGIN_PASSWORD = "LOGIN_PASSWORD";
	/**
	 * 商户用户-key
	 */
	public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";
	/**
	 * 农场ID
	 */
	public static final String FARM_ID = "FARM_ID";
	/**
	 * 默认头像相对路径
	 */
	public static final String DEFAULT_HEADIMG_PATH = "user/headimg/default.png";
	/**
	 * 默认密码
	 */
	public static final String DEFAULT_PASSWORD = "123456";
	/**
	 * 登录类型-商户（超管）
	 */
	public static final String MERCHANT = "merchant";
	/**
	 * 登录类型-普通用户
	 */
	public static final String USER = "user";
}
