package com.example.sports.util;

import org.apache.commons.lang.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 *
 * @author jacob
 */
public class DateUtil {

    public static final String YYYY_MM_DD        = "yyyy-MM-dd";

    public static final String DEFAULT_PATTERN   = "yyyy-MM-dd HH:mm:ss";

    public static final String FULL_TIME         = "yyyyMMddHHmmss";

    public static final String TIME              = "HH:mm";

    public static final String MONTH_TIME        = "MM-dd HH:mm";

    public static final String SCHEDULE_TIME     = "yyyy.MM.dd HH:mm";

    public static final String SCHEDULE_TIME_SMS = "yyyy年MM月dd日 HH时mm分";

    public static final String yyyy_MM           = "yyyy-MM";

    /**
     * Calendar实例
     */
    private static Calendar    calendar;

    // public static String serviceTimeExpect(Date date) {
    // if (date == null) {
    // return null;
    // }
    // Calendar date = Calendar.getInstance();
    // SimpleDateFormat sdf = new SimpleDateFormat(SERVICE_TIME_EXPECT);
    // sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    // Date dateTime =sdf.parse(date);
    // date.setTime(dateTime);
    // }

    private static Calendar getCalendarInstance() {
        if (calendar != null) {
            return calendar;
        } else {
            return Calendar.getInstance();
        }
    }

    /**
     * 转成格式 yyyy-MM-dd HH:mm
     *
     * @param date
     * @return
     */
    public static String scheduleTime(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, SCHEDULE_TIME);
    }

    public static Date getCurrentTime() {
        return new Date();
    }

    /**
     * 转成默认格式yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, DEFAULT_PATTERN);
    }

    /**
     * 转成格式 HH:mm
     *
     * @param date
     * @return
     */
    public static String time(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, TIME);
    }

    /**
     * 转成格式 HH:mm
     *
     * @param date
     * @return
     */
    public static String sms(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, SCHEDULE_TIME_SMS);
    }

    /**
     * 转成格式 MM-dd HH:mm
     * 
     * @param date
     * @return
     */
    public static String monAndTime(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, MONTH_TIME);
    }

    /**
     * 转成格式 yyyy-MM
     *
     * @param date
     * @return
     */
    public static String month(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, yyyy_MM);
    }

    /**
     * 将Date类型的日期转换为参数定义的格式的字符串。
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }

        return DateFormatUtils.format(date, pattern);
    }

    public static Date getCurrDate(String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            String currDateStr = df.format(new Date());
            return df.parse(currDateStr);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 封装开始时间
     * 
     * @param date
     * @return
     */
    public static Date beginDate(Date date) {
        if (null == date) {
            return null;
        }
        String beginDate = new SimpleDateFormat(YYYY_MM_DD).format(date) + " 00:00:00";
        try {
            return new SimpleDateFormat(DEFAULT_PATTERN).parse(beginDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 封装结束时间
     * 
     * @param date
     * @return
     */
    public static Date endDate(Date date) {
        if (null == date) {
            return null;
        }
        String endDate = new SimpleDateFormat(YYYY_MM_DD).format(date) + " 23:59:59";
        try {
            return new SimpleDateFormat(DEFAULT_PATTERN).parse(endDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param nowDate
     * @param day
     *            负数表示减
     * @return
     */
    public static Date addDateByDay(Date nowDate, int day) {
        Calendar calendar = getCalendarInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * @param nowDate
     * @param hour
     *            负数表示减
     * @return
     */
    public static Date addDateByHour(Date nowDate, int hour) {
        Calendar calendar = getCalendarInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * @param nowDate
     * @param year
     *            增加年份 负数表示减
     * @return
     */
    public static Date addDateByYear(Date nowDate, int year) {
        Calendar calendar = getCalendarInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * String转Date
     * 
     * @param strDate
     * @return
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (null == strDate) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date stringToDate(String strDate) {
        return stringToDate(strDate, DEFAULT_PATTERN);
    }

    /**
     * 上周一统计开始时间00
     * 
     * @return
     */
    public static Date lastWednesday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return beginDate(cal.getTime());
    }

    /**
     * 上周日统计结束时间 12
     * 
     * @return
     */
    public static Date lastSunday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()) + " 12:00:00";
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据日期取得星期几
     * 
     * @Date 2016年12月14日,下午11:38:01
     * @author YYF
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int weekIndex = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (weekIndex < 0) {
            weekIndex = 0;
        }
        return weeks[weekIndex];
    }

    /**
     * 比较两个日期的大小
     * 
     * @param date1
     * @param date2
     * @return 1. date1>date2 0.date1=date2 -1.date1<date2
     */
    public static int compareDate(Date date1, Date date2) {
        int ret = 0;
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        if (time1 > time2) {
            ret = 1;
        } else if (time1 == time2) {
            ret = 0;
        } else {
            ret = -1;
        }
        return ret;
    }

    /**
     * 计算当前时间和指定时间之间相差的小时
     * 
     * @param smdate
     *            指定的时间
     * @throws ParseException
     */
    public static int hourBetween(String smdate) {
        Date start = new Date();
        Date end = stringToDate(smdate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        long time1 = cal.getTimeInMillis();
        cal.setTime(end);
        long time2 = cal.getTimeInMillis();

        long betweenDays = (time2 - time1) / (1000 * 3600);

        return Integer.parseInt(String.valueOf(betweenDays));
    }

    // public static void main(String[] args) {
    //// System.out.println(hourBetween("2017-01-13 00:00:00"));
    // System.out.println(format(addDateByYear(new Date(), -7)));
    // }

    /**
     * 比较两个日期相差的天数
     * 
     * @author LHB
     * @param createTime
     * @param today
     * @return
     */
    public static Object differentDays(Date createTime, Date today) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(createTime);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(today);
        int createDay = cal1.get(Calendar.DAY_OF_MONTH);
        int todayDay = cal2.get(Calendar.DAY_OF_MONTH);
        int createMonth = cal1.get(Calendar.MONTH);
        int todayMonth = cal2.get(Calendar.MONTH);
        int createYear = cal1.get(Calendar.YEAR);
        int todayYear = cal2.get(Calendar.YEAR);
        // 同一年
        if (createYear == todayYear) {
            if (createMonth == todayMonth) {
                if (todayDay - createDay == 0) {
                    return "今天" + " " + time(createTime);
                }
                if (todayDay - createDay == 1) {
                    return "昨天" + " " + time(createTime);
                }

            }
            return monAndTime(createTime);
            // 不同年
        } else {
            return format(createTime);
        }
    }

    /**
     * 比较是否同年同月
     *
     * @author LHB
     * @param createTime
     * @param today
     * @return
     */
    public static String differentMonths(Date createTime, Date today) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(createTime);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(today);
        int createMonth = cal1.get(Calendar.MONTH);
        int todayMonth = cal2.get(Calendar.MONTH);
        int createYear = cal1.get(Calendar.YEAR);
        int todayYear = cal2.get(Calendar.YEAR);
        // 同一年
        if (createYear == todayYear) {
            if (createMonth == todayMonth) {
                return "SAME_MONTH";
            }
        } else // 不同年
        {
            return "DIFFERENT_YEAR";
        }
        return null;
    }

    /**
     * 校验是否同年同月
     *
     * @author LHB
     * @param updateTime
     * @param today
     * @return
     */
    public static String differentMonth(Date updateTime, Date today) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(updateTime);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(today);

        int createMonth = cal1.get(Calendar.MONTH);
        int todayMonth = cal2.get(Calendar.MONTH);
        int createYear = cal1.get(Calendar.YEAR);
        int todayYear = cal2.get(Calendar.YEAR);
        // 同一年
        if (createYear == todayYear) {
            if (createMonth == todayMonth) {
                return "same";
            } else {
                return "different";
            }
        } else // 不同年
        {
            return "different";
        }
    }

    /**
     * 返回前六个月
     * 
     * @param date
     * @return
     */
    public static List<Date> getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        List<Date> dates = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Date month = calendar.getTime();
            dates.add(month);
            calendar.add(Calendar.MONTH, -1);
        }
        return dates;
    }

    /**
     * 返回上一个月
     *
     * @param date
     * @return
     */

    public static Date lastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date month = calendar.getTime();

        return month;
    }

    /**
     * 获取月份天数
     * 
     * @param date
     * @return
     */
    public static Integer getMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        calendar.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        return calendar.get(Calendar.DATE);
    }

    /**
     * 增加相应的月份
     * 
     * @param date
     * @param size
     * @return
     */
    public static Date addMonth(Date date, int size) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, size);
        return calendar.getTime();
    }

}
