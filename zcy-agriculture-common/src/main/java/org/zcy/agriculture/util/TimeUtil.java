package org.zcy.agriculture.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeUtil {
	public static String nextDate(String date) {
		Calendar ca = Calendar.getInstance();
//		ca.setTime(DateUtil.StringtoDate(nian + "-" + yue + "-01", "yyyy-MM-dd"));
//		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
//		ca.set(Calendar.HOUR_OF_DAY, 23);// 时
//		ca.set(Calendar.MINUTE, 59);// 分
//		ca.set(Calendar.SECOND, 59);// 秒
		
		ca.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7))-1, Integer.parseInt(date.substring(8, 10)));
		ca.add(Calendar.DATE, 1);// 日期加1
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(ca.getTime());
	}
	/**
	 * 取得当月天数
	 */
	public static int getCurrentMonthLastDay(Date date) {
		Calendar a = Calendar.getInstance();
		a.setTime(date);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
	public static void main(String[] args) {
//		System.out.println(nextDate("2019-05-20"));
		String c1="2019-05-31";
		String c2="2018-05-31";
		List<String> l = new ArrayList<>();
		while (!c1.equals(c2)) {
			System.out.println(c2);
			l.add(c2);
			c2 = nextDate(c2);
		}
		System.out.println(l.size());
	}
}
