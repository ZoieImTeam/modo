package org.srr.dev.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/12/14.
 */
public class TimeUtil {
    public static String getStandardDate(String strs) {
        StringBuffer sb = new StringBuffer();

        long t = getStringToDate(strs);

        long time = System.currentTimeMillis() - t;
        long mill = time / 1000;//秒前

        long minute = time / 60 / 1000;// 分钟前

        long hour = time / 60 / 60 / 1000;// 小时

        long day = time / 24 / 60 / 60 / 1000;// 天前


        if (day > 0) {
            Date d = new Date(t);
            SimpleDateFormat sf = new SimpleDateFormat("MM/dd");
            return sf.format(d);
//            sb.append(day + "天");
        } else if (hour > 0) {
            if (hour >= 24) {
                sb.append("1天");
            } else {
                sb.append(hour + "小时");
            }
        } else if (minute > 0) {
            if (minute == 60) {
                sb.append("1小时");
            } else {
                sb.append(minute + "分钟");
            }
        } else if (mill > 0) {
            if (mill == 60) {
                sb.append("1分钟");
            } else {
                sb.append(mill + "秒");
            }
        } else {
            sb.append("刚刚");
        }
        if (!sb.toString().equals("刚刚")) {
            sb.append("前");
        }
        return sb.toString();

    }

    public static String getCurrentTime() {
        return getDateToString(System.currentTimeMillis());
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }
    /*将字符串转为时间戳不带秒数*/
    public static long getStringToDateWithoutS(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String getCurrentDate() {
        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    public static String getStandardDateWithoutS(String strs) {
        StringBuffer sb = new StringBuffer();

        long t = getStringToDateWithoutS(strs);

        long time = System.currentTimeMillis() - t;
        long mill = time / 1000;//秒前

        long minute = time / 60 / 1000;// 分钟前

        long hour = time / 60 / 60 / 1000;// 小时

        long day = time / 24 / 60 / 60 / 1000;// 天前


        if (day > 0) {
            Date d = new Date(t);
            SimpleDateFormat sf = new SimpleDateFormat("MM/dd");
            return sf.format(d);
//            sb.append(day + "天");
        } else if (hour > 0) {
            if (hour >= 24) {
                sb.append("1天");
            } else {
                sb.append(hour + "小时");
            }
        } else if (minute > 0) {
            if (minute == 60) {
                sb.append("1小时");
            } else {
                sb.append(minute + "分钟");
            }
        } else if (mill > 0) {
            if (mill == 60) {
                sb.append("1分钟");
            } else {
                sb.append(mill + "秒");
            }
        } else {
            sb.append("刚刚");
        }
        if (!sb.toString().equals("刚刚")) {
            sb.append("前");
        }
        return sb.toString();

    }

}
