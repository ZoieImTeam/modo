package org.srr.dev.util;

/**
 * Created by Administrator on 2015/12/11.
 */
public class TextUtils {


    /**
     * String 为空或者长度为0或 "null" 时返回true
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == null || "\"\"".equals(str) || "null".equals(str) || str.length() == 0)
            return true;
        else
            return false;
    }

    /**
     * String 为空或者长度为0或 "null" 时返回true
     */
    public static boolean isEmptyChen(CharSequence str) {
        if ("\"\"".equals(str.toString()) || "null".equals(str) || str == null || str.length() == 0 || "".equals(str.toString()))
            return true;
        else
            return false;
    }

    /**
     * String 为空或者长度为0或 "null" 时返回
     */
    public static String isEmptyString(CharSequence str) {
        if (str == null || "\"\"".equals(str.toString()) || "null".equals(str.toString()) || str.length() == 0 || "".equals(str.toString())) {
            return "未知";
        } else {
            return str.toString();
        }
    }

    /**
     * String 为空或者长度为0或 "null" 时返回
     */
    public static String isEmptyAge(int age) {
        if (age > 0) {
            return age + "";
        } else {
            return "保密";
        }
    }
}
