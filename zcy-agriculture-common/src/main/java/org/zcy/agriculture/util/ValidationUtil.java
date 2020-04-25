package org.zcy.agriculture.util;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;


/**
 * @author: 大数据应用开发- zb.fang
 * @date: 2018-09-10 14:02
 * @version: 1.0
 */
public class ValidationUtil {
	/**
	 *
	 * 验证值是否是空
	 *
	 * @param object 验证值
	 * @return 验证值是否是空
	 */
	public static boolean isEmpty(Object object) {
		boolean isEmpty = false;

		if (object == null) {
			isEmpty = true;
		}

		// 验证字符串类型
		else if (object instanceof String) {
			String validatedObject = (String) object;
			if (validatedObject == null || "".equals(validatedObject.trim())) {
				isEmpty = true;
			}
		}

		// 验证集合类型
		else if (object instanceof Collection) {
			Collection validatedObject = (Collection) object;
			if (validatedObject == null || validatedObject.size() == 0) {
				isEmpty = true;
			}
		}

		// 验证Map类型
		else if (object instanceof Map) {
			Map validatedObject = (Map) object;
			if (validatedObject == null || validatedObject.size() == 0) {
				isEmpty = true;
			}
		}

		// 验证日期类型
		else if (object instanceof Date) {
			Date validatedObject = (Date) object;
			if (validatedObject == null) {
				isEmpty = true;
			}
		}

		// 验证日期类型
		else if (object instanceof Timestamp) {
			Timestamp validatedObject = (Timestamp) object;
			if (validatedObject == null) {
				isEmpty = true;
			}
		}

		// 验证Set类型
		else if (object instanceof Set) {
			Set validatedObject = (Set) object;
			if (validatedObject == null || validatedObject.size() == 0) {
				isEmpty = true;
			}
		}

		return isEmpty;
	}

	/**
	 *
	 * 验证数组是否为空
	 *
	 * @param objects 对象数组
	 * @return
	 */
	public static boolean isEmpty(Object[] objects) {
		boolean isEmpty = false;

		if (objects == null || objects.length == 0) {
			isEmpty = true;
		}

		return isEmpty;
	}

	/**
	 * 判断length位的字符串是否为 格式正确的正整数
	 * 
	 * @param target 待判断的字符串
	 * @param length 字符串长度
	 * @return
	 */
	public static boolean isNumeric(String target, Integer length) {
		String reg = "[1-9][0-9]{0,length}";
		Integer len = length - 1;
		Pattern pattern = Pattern.compile(reg.replace("length", len.toString()));
		return pattern.matcher(target).matches();
	}

	public static boolean isJson(Object obj) {
		if (obj.getClass().getName().indexOf("java.lang") >= 0 || obj.getClass().getName().indexOf("java.util") >= 0 || obj.getClass().getName().indexOf("org.zcy.agriculture") >= 0) {
			return true;
		}
		return false;
	}
}
