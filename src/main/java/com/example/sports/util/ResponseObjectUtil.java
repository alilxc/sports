package com.example.sports.util;

import com.example.sports.constant.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 * 响应返回数据工具类
 */
public class ResponseObjectUtil {

    public static <T> ResponseObject<T> success(T data) {
        ResponseObject<T> entity = new ResponseObject<T>();
        entity.setData(data);
        entity.setCode(Errors.SUCCESS.code);
        entity.setHttpStatus(HttpStatusCode.OK.value());
        entity.setTimestamp(System.currentTimeMillis());
        return entity;
    }

    public static <T> ResponseObject<T> success() {
        ResponseObject<T> entity = new ResponseObject<T>();
        entity.setTimestamp(System.currentTimeMillis());
        entity.setCode(Errors.SUCCESS.code);
        entity.setHttpStatus(HttpStatusCode.OK.value());
        return entity;
    }

    public static ResponseObject<Void> fail(Integer httpStatus, Integer code, String message, HttpServletRequest request) {
        ResponseObject<Void> entity = build();
        entity.setCode(code);
        entity.setHttpStatus(httpStatus);
        entity.setException(message);
        if (null != request) {
            entity.setPath(request.getRequestURI());
        }
        return entity;
    }

    public static <T> ResponseObject<T> fail(Integer code, String message) {
        ResponseObject<T> entity = new ResponseObject<T>();
        entity.setTimestamp(System.currentTimeMillis());
        entity.setCode(code);
        entity.setException(message);
        return entity;
    }

    public static <T> ResponseObject<T> fail(String message) {
        ResponseObject<T> entity = new ResponseObject<T>();
        entity.setTimestamp(System.currentTimeMillis());
        entity.setCode(Errors.SYSTEM_CUSTOM_ERROR.code);
        entity.setException(message);
        return entity;
    }

    public static <T> ResponseObject<T> fail(Errors error) {
        ResponseObject<T> entity = new ResponseObject<T>();
        entity.setTimestamp(System.currentTimeMillis());
        entity.setCode(error.code);
        entity.setException(error.label);
        return entity;
    }

    private static ResponseObject<Void> build() {
        ResponseObject<Void> entity = new ResponseObject<Void>();
        entity.setTimestamp(System.currentTimeMillis());
        return entity;
    }

}
