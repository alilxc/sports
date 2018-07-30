package com.example.sports.util;


import com.example.sports.constant.Errors;
import com.example.sports.exception.BusinessException;

/**
 * 异常工具类
 *
 */
public class ExceptionUtil {

    public static void throwException(int code, String codeLabel) {
        BusinessException e = new BusinessException(code, codeLabel, codeLabel);
        throw e;
    }

    public static void throwException(Errors error) {
        BusinessException e = new BusinessException(error, error.label);
        throw e;
    }

    public static void throwException(Errors error, String msg) {
        BusinessException e = new BusinessException(error, msg);
        throw e;
    }

}
