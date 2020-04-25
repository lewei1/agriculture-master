package org.zcy.agriculture.util;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 时间工具类
 * 
 * @author numberone
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     * 
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     * 
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }
    
    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
    public static boolean comperDate(Date beforeDate, Date endDate) {
		long btime = beforeDate.getTime();
		long eime = endDate.getTime();
		if (eime > btime) {
			return true;
		}
		return false;
	}
    
    /**
     * 时间添加小时
     * @param startDate
     * @param hour
     * @return
     */
    public static Date addDateHour(Date startDate , double hour) {
    	String[] hs = String.valueOf(hour).split("\\.");
    	int h = Integer.parseInt(hs[0]);
    	int m = 0;
    	if(hs.length == 2 && !hs[1].equals("0")) {
    		m = (int) (Double.parseDouble("0."+hs[1]) * 60);
    	}
    	Calendar cal = Calendar.getInstance();   
        cal.setTime(startDate);
        if(h > 0) {
        	 cal.add(Calendar.HOUR, h);// 24小时制   
        }
        if(m > 0) {
        	cal.add(Calendar.MINUTE, m);// 24小时制   
        }
        return cal.getTime();
    }

    /**
     * 时间添加分钟
     * @param startDate
     * @param mnute
     * @return
     */
    public static Date addDateMinute(Date startDate , double mnute) {
        String[] hs = String.valueOf(mnute).split("\\.");
        int m = Integer.parseInt(hs[0]);
        int s = 0;
        if(hs.length == 2 && !hs[1].equals("0")) {
            s = Integer.parseInt((Double.parseDouble(("0."+hs[1])) * 60) + "");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        if(m > 0) {
            cal.add(Calendar.MINUTE, m);// 24小时制
        }
        if(s > 0) {
            cal.add(Calendar.SECOND, s);// 24小时制
        }
        return cal.getTime();
    }

    /**
     * 时间添加天
     * @param startDate
     * @param hour
     * @return
     */
    public static Date addDateDay(Date startDate , int day) {
    	Calendar cal = Calendar.getInstance();   
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 获取过去某一天的开始时间
     * @param index 过去的天数，比如index为2，则表示获取两天前的开始时间
     * @return Date
     */
    public static Date getLastDayByIndex(int index) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH , - index);
        Date start = calendar.getTime();
        return start;
    }
    /**
     * 把时间根据时、分、秒转换为时间段
     *
     * @param StrDate
     */
    public static String getTimes(Date date) {
        String resultTimes = "";
        Date now;
        try {
            now = new Date();
            long times = now.getTime() - date.getTime();
            long day = times / (24 * 60 * 60 * 1000);
            long hour = (times / (60 * 60 * 1000) - day * 24);
            long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
//            long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

            StringBuffer sb = new StringBuffer();
            //sb.append("发表于：");
            if (day > 2) {
            	sb.append(dateTime(date));
            }else if (day > 0) {
            	sb.append(day + "天前");
            }else if (hour > 0) {
                sb.append(hour + "小时前");
            } else if (min > 0) {
                sb.append(min + "分钟前");
            } else {
                sb.append("刚刚");
            }

            resultTimes = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultTimes;
    }
    public static String getHourAndMinute(Long time,final String format) {
    	Date now = new Date(time);
        return DateFormatUtils.format(now, format);
    }

    /**
     * 比较两个日期之间的大小
     *
     * @param d1
     * @param d2
     * @return 前者大于后者返回true 反之false
     */
    public static boolean compareDate(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);

        int result = c1.compareTo(c2);
        if (result >= 0)
            return true;
        else
            return false;
    }
}
