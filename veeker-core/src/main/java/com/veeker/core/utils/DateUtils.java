package com.veeker.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 *
 * @author ：qiaoliang
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static final long ONE_MILLIS = 1000;
    private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR = 3600;
    private static final long ONE_DAY = 86400;
    private static final long ONE_MONTH = 2592000;
    private static final long ONE_YEAR = 31104000;


    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM",
            "yyyyMMdd", "yyyyMMddHHmmss", "yyyyMMddHHmm", "yyyyMM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     *
     * @author ：qiaoliang
     * @return java.lang.String
     */
    public static String formatCurrentDate() {
        return formatCurrentDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     *
     * @author ：qiaoliang
     * @param pattern : 时间格式
     * @return java.lang.String
     */
    public static String formatCurrentDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     *
     * @author ：qiaoliang
     * @param date : 时间
     * @param pattern : 时间格式
     * @return java.lang.String
     */
    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (pattern != null) {
            formatDate = DateFormatUtils.format(date, pattern);
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }


    /**
     * 获取当前时间戳（yyyyMMddHHmmss）
     *
     * @author ：qiaoliang
     * @return long
     */
    public static long getCurrentTimestamp() {
        long nowTimeStamp = Long.parseLong(getCurrentTimestampStr());
        return nowTimeStamp;
    }

    /**
     * 获取当前时间戳（yyyyMMddHHmmss）
     *
     * @author ：qiaoliang
     * @return java.lang.String
     */
    public static String getCurrentTimestampStr() {
        return formatDate(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 获取Unix时间戳
     *
     * @author ：qiaoliang
     * @return long
     */
    public static long getCurrentUnixTimestamp() {
        long nowTimeStamp = System.currentTimeMillis() / 1000;
        return nowTimeStamp;
    }

    /**
     * 获取Unix时间戳
     *
     * @author ：qiaoliang
     * @return java.lang.String
     */
    public static String getCurrentUnixTimestampStr() {
        return String.valueOf(getCurrentUnixTimestamp());
    }

    /**
     * 转换Unix时间戳
     *
     * @author ：qiaoliang
     * @param time : 时间戳
     * @return long
     */
    public static long parseUnixTimeStamp(long time) {
        return time / ONE_MILLIS;
    }

    /**
     * 获取前一周
     *
     * @author ：qiaoliang
     * @param date : 时间
     * @return java.util.Date
     */
    public static Date getBeforeWeek(Date date) {
        return getAddDate(date, Calendar.WEEK_OF_YEAR, -1);
    }

    /**
     * 获取前一天
     *
     * @author ：qiaoliang
     * @param date : 时间
     * @return java.util.Date
     */
    public static Date getBeforeDay(Date date) {
        return getAddDate(date, Calendar.DAY_OF_YEAR, -1);
    }

    /**
     * 获取前一月
     *
     * @author ：qiaoliang
     * @param date : 时间
     * @return java.util.Date
     */
    public static Date getBeforeMouth(Date date) {
        return getAddDate(date, Calendar.MONTH, -1);
    }

    /**
     * 获取前一年
     *
     * @author ：qiaoliang
     * @param date : 时间
     * @return java.util.Date
     */
    public static Date getBeforeYear(Date date) {
        return getAddDate(date, Calendar.YEAR, -1);
    }


    /**
     * 获取前一周
     *
     * @author ：qiaoliang
     * @param date : 时间
     * @return java.util.Date
     */
    public static Date getAfterWeek(Date date) {
        return getAddDate(date, Calendar.WEEK_OF_YEAR, 1);
    }

    /**
     * 获取前一天
     *
     * @author ：qiaoliang
     * @param date : 时间
     * @return java.util.Date
     */
    public static Date getAfterDay(Date date) {
        return getAddDate(date, Calendar.DAY_OF_YEAR, 1);
    }

    /**
     * 获取前一月
     *
     * @author ：qiaoliang
     * @param date : 时间
     * @return java.util.Date
     */
    public static Date getAfterMouth(Date date) {
        return getAddDate(date, Calendar.MONTH, 1);
    }

    /**
     * 获取前一年
     *
     * @author ：qiaoliang
     * @param date : 时间
     * @return java.util.Date
     */
    public static Date getAfterYear(Date date) {
        return getAddDate(date, Calendar.YEAR, 1);
    }


    /**
     * 增加日期
     *
     * @author ：qiaoliang
     * @param date 时间
     * @param field  Calendar.MONTH,Calendar.DAY_OF_YEAR
     * @param amount 正数为将来时间, 负数为过去时间
     * @return java.util.Date
     */
    public static Date getAddDate(Date date, int field, int amount) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(field, amount);
        Date dateFrom = cl.getTime();
        return dateFrom;
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     *
     * @author ：qiaoliang
     * @return java.lang.String
     */
    public static String formatTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     *
     * @author ：qiaoliang
     * @return java.lang.String
     */
    public static String formatCurrentDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     *
     * @author ：qiaoliang
     * @return java.lang.String
     */
    public static String formatYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     *
     * @author ：qiaoliang
     * @return java.lang.String
     */
    public static String formatMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     *
     * @author ：qiaoliang
     * @return java.lang.String
     */
    public static String formatDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     *
     * @author ：qiaoliang
     * @return java.lang.String
     */
    public static String formatWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 获取过去的天数
     *
     * @author ：qiaoliang
     * @param date : 时间
     * @return long
     */
    public static long getBeforeDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (ONE_DAY * ONE_MILLIS);
    }

    /**
     * 获取过去的小时
     *
     * @author ：qiaoliang
     * @param date : 时间
     * @return long
     */
    public static long getBeforeHours(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (ONE_HOUR * ONE_MILLIS);
    }

    /**
     * 获取过去的分钟
     *
     * @author ：qiaoliang
     * @param date : 时间
     * @return long
     */
    public static long getBeforeMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (ONE_MINUTE * ONE_MILLIS);
    }

    /**
     * 获取过去的秒
     *
     * @author ：qiaoliang
     * @param date : 时间
     * @return long
     */
    public static long getBeforeSeconds(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / ONE_MILLIS;
    }

    /**
     *  获取两个日期之间的天数
     *
     * @author ：qiaoliang
     * @param before : 开始时间
     * @param after : 结束时间
     * @return double
     */
    public static double getDays(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (ONE_MILLIS * ONE_DAY);
    }


    /**
     * 距离今天多久
     *
     * @author ：qiaoliang
     * @param createAt : 时间
     * @return java.lang.String
     */
    public static String formatTextFromtoday(Date createAt) {
        // 定义最终返回的结果字符串。
        String interval = null;
        if (createAt == null) {
            return "";
        }
        long millisecond = System.currentTimeMillis() - createAt.getTime();

        long second = millisecond / ONE_MILLIS;

        if (second <= 0) {
            second = 0;
        }
        //*--------------微博体（标准）
        if (second == 0) {
            interval = "刚刚";
        } else if (second < ONE_MINUTE / 2) {
            interval = second + "秒以前";
        } else if (second >= ONE_MINUTE / 2 && second < ONE_MINUTE) {
            interval = "半分钟前";
        } else if (second >= ONE_MINUTE && second < ONE_MINUTE * ONE_MINUTE) {
            //大于1分钟 小于1小时
            long minute = second / ONE_MINUTE;
            interval = minute + "分钟前";
        } else if (second >= ONE_HOUR && second < ONE_DAY) {
            //大于1小时 小于24小时
            long hour = (second / ONE_MINUTE) / ONE_MINUTE;
            interval = hour + "小时前";
        } else if (second >= ONE_DAY && second <= ONE_DAY * 2) {
            //大于1D 小于2D
            interval = "昨天" + formatDate(createAt, "HH:mm");
        } else if (second >= ONE_DAY * 2 && second <= ONE_DAY * 7) {
            //大于2D小时 小于 7天
            long day = ((second / ONE_MINUTE) / ONE_MINUTE) / 24;
            interval = day + "天前";
        } else if (second <= ONE_DAY * 365 && second >= ONE_DAY * 7) {
            //大于7天小于365天
            interval = formatDate(createAt, "MM-dd HH:mm");
        } else if (second >= ONE_DAY * 365) {
            //大于365天
            interval = formatDate(createAt, "yyyy-MM-dd HH:mm");
        } else {
            interval = "0";
        }
        return interval;
    }


    /**
     * 距离截止日期还有多长时间
     *
     * @author ：qiaoliang
     * @param date : 时间
     * @return java.lang.String
     */
    public static String formatTextFromDeadline(Date date) {
        long deadline = date.getTime() / ONE_MILLIS;
        long now = (System.currentTimeMillis()) / ONE_MILLIS;
        long remain = deadline - now;
        if (remain <= ONE_HOUR) {
            return "只剩下" + remain / ONE_MINUTE + "分钟";
        } else if (remain <= ONE_DAY) {
            return "只剩下" + remain / ONE_HOUR + "小时"
                    + (remain % ONE_HOUR / ONE_MINUTE) + "分钟";
        } else {
            long day = remain / ONE_DAY;
            long hour = remain % ONE_DAY / ONE_HOUR;
            long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return "只剩下" + day + "天" + hour + "小时" + minute + "分钟";
        }

    }

    /**
     * Unix时间戳转换成指定格式日期字符串
     *
     * @author ：qiaoliang
     * @param timestampString : 时间戳 如："1473048265";
     * @param pattern : 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     * @return java.lang.String
     */
    public static String unixTimeStamp2Date(String timestampString, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        Long timestamp = Long.parseLong(timestampString) * ONE_MINUTE;
        String date = new SimpleDateFormat(pattern, Locale.CHINA).format(new Date(timestamp));
        return date;
    }

    /**
     * 日期格式字符串转换成Unix时间戳
     *
     * @author ：qiaoliang
     * @param dateStr 字符串日期
     * @param pattern 如：yyyy-MM-dd HH:mm:ss
     * @return 字符串
     */
    public static String date2UnixTimeStamp(String dateStr, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return String.valueOf(sdf.parse(dateStr).getTime() / ONE_MINUTE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
