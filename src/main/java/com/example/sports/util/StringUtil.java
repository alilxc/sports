package com.example.sports.util;


import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author jacob
 */
public class StringUtil {

    public static String trim(String str) {
        if (str == null) {
            return "";
        }
        return str.trim();
    }

    /**
     * 判断A字符串不为NULL并且equals字符串B
     * 
     * @Date in Apr 14, 2011,5:40:43 PM
     * @param valueA
     * @param valueB
     * @return
     */
    public static boolean strEquals(String valueA, String valueB) {
        boolean eq = (!isBlank(valueA) && !isBlank(valueB)) && (valueA.trim().equals(valueB.trim()));
        if (eq) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * 判断是否为空，为空则返回true
     * 为空的条件：null、""、"null"
     * </pre>
     * 
     * @param obj
     * @return
     */
    public static boolean isBlank(Object obj) {
        if (obj == null) {
            return true;
        }
        String str = obj.toString().trim();
        if ("".equals(str) || "null".equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * 判断是否不为空，不为空则返回true
     * 为空的条件：null、""、"null"
     * </pre>
     * 
     * @param obj
     * @return
     */
    public static boolean isNotBlank(Object obj) {
        return !isBlank(obj);
    }

    /**
     * <pre>
     * 获取换行符
     * </pre>
     * 
     * @return
     */
    public static String getNewLine() {
        return System.getProperty("line.separator");
    }

    /**
     * 获取uuid字符串
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取uuid字符串，无横杠
     */
    public static String uuidNotLine() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 去除前后空格，若obj为null返回""空字串
     * 
     * @param obj
     * @return
     */
    public static String trim(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString().trim();
    }

    /**
     * <p>
     * 生成随机码
     *
     * <p>
     * 若指定的长度为0，则返回空白字符串
     *
     * @param codeLength
     *            随机码长度
     * @return
     */
    public static String randomCode(int codeLength) {

        return RandomStringUtils.randomNumeric(codeLength);
    }

    /**
     * 取uuid去掉-后从后向前截取指定长度随机串 @Date 2016年3月19日,上午12:57:40 @author YYF @param length @return @return
     * String @throws
     */
    public static String getUUID(int length) {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        if (uuid.length() > length) {
            uuid = uuid.substring(uuid.length() - length, uuid.length());
        }
        return uuid;
    }

    /**
     * 判断字符串是否为数字
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (null == str || str.trim().length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查指定的字符串是否为空。
     * <ul>
     * <li>SysUtils.isEmpty(null) = true</li>
     * <li>SysUtils.isEmpty("") = true</li>
     * <li>SysUtils.isEmpty(" ") = true</li>
     * <li>SysUtils.isEmpty("abc") = false</li>
     * </ul>
     * 
     * @param value
     *            待检查的字符串
     * @return true/false
     */
    public static boolean isEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(value.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查对象是否为数字型字符串,包含负数开头的。
     */
    public static boolean isNumeric(Object obj) {
        if (obj == null) {
            return false;
        }
        char[] chars = obj.toString().toCharArray();
        int length = chars.length;
        if (length < 1) {
            return false;
        }

        int i = 0;
        if (length > 1 && chars[0] == '-') {
            i = 1;
        }

        for (; i < length; i++) {
            if (!Character.isDigit(chars[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查指定的字符串列表是否不为空。
     */
    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }

    /**
     * 把通用字符编码的字符串转化为汉字编码。
     */
    public static String unicodeToChinese(String unicode) {
        StringBuilder out = new StringBuilder();
        if (!isEmpty(unicode)) {
            for (int i = 0; i < unicode.length(); i++) {
                out.append(unicode.charAt(i));
            }
        }
        return out.toString();
    }



    public static String numFormat(double d, String format) {
        return new DecimalFormat(format).format(d);
    }

    public static String numFormat(double d) {
        return numFormat(d, "#0.00");
    }

    /**
     * yyyyMMddHHmmss+param+6位随机数
     * 
     * @param param
     * @return
     */
    public static String serialNum(Long param) {
        return DateUtil.format(new Date(), "yyyyMMddHHmmss") + param + (int) ((Math.random() * 9 + 1) * 100000);
    }

    /**
     * 返回对象所有值为null的字段 String空串也是为null
     * 
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            boolean empty = srcValue == null || ("".equals(srcValue.toString().trim()));
            if (empty) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
