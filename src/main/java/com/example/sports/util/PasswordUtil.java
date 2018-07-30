package com.example.sports.util;

import org.apache.commons.lang.StringUtils;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-04-25
 */
public class PasswordUtil {
    private static final String SALT = "sports@WCTCH-Luke";

    /**
     * 密码加密
     *
     * @param password
     * @return
     */
    public static String encode(String password) {
        return Md5Util.md5(password + SALT);
    }

    /**
     * 判断输入密码是否匹配
     *
     * @param password
     *            输入的密码
     * @param passwordEncoded
     *            已加密的密码
     * @return
     */
    public static boolean match(String password, String passwordEncoded) {
        return StringUtils.equals(encode(password), passwordEncoded);
    }
}
