package org.zcy.agriculture.util;

import org.zcy.agriculture.constants.RedisConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 拼接rediskey公共方法
 * @author hp
 *
 */
public class RedisKeyUtils {


	/**
	 * 验证码key
	 * @param phone
	 * @param type
	 * @return
	 */
	public static String getVerificationCode(String phone , String type) {
		return RedisConstants.VERIFICATION_CODE + phone + "_" + type;
	}

	/**
	 * 登录key
	 * @param openId
	 * @return
	 */
	public static String getLoginTokenKey(String openId) {
		return RedisConstants.LOGIN_TOKEN + openId;
	}


	/**
	 * 物联网token的key
	 * @return
	 */
	public static String getIOTTokenKey() {
		return RedisConstants.IOT_TOKEN ;
	}

	/**
	 * 获取商户（超级管理员，农场主）信息的key
	 * @param phone
	 * @return
	 */
	public static String getLoginUserKey(String phone) {
		return RedisConstants.MERCHANT_LOGIN + phone + ":" + UUIDUtils.generateUuid();
	}

	public static String getCurrentFarmIdKey(String phone) {
		//农场必须与当前登录的用户绑定
		return getLoginUserKey(phone) + ":" + RedisConstants.FARM_TOKEN;
	}

}
