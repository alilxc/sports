package com.example.sports.util;

import io.swagger.annotations.ApiModelProperty;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * <pre>
 * API请求的返回结果
 * </pre>
 */
public class ResponseObject<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "接口数据对象")
    private T                 data;

    @ApiModelProperty(value = "接口状态编码，0为正常")
    private int               code;

    @ApiModelProperty(value = "http状态编码")
    private int               httpStatus;

    @ApiModelProperty(value = "接口路径")
    private String            path;

    @ApiModelProperty(value = "接口访问时间")
    private Long              timestamp;

    @ApiModelProperty(value = "接口异常信息")
    private String            exception;

    /**
     * @param httpStatus
     *            HTTP状态码
     * @param code
     *            业务代码
     * @param request
     *            request对象
     * @return 响应体
     */
    public static <E> ResponseObject<E> build(Integer httpStatus, Integer code, HttpServletRequest request) {
        return build(httpStatus, code, null, null, request);
    }

    /**
     * @param httpStatus
     *            HTTP代码
     * @param code
     *            业务代码
     * @param data
     *            随错误响应体返回的相关错误数据。
     * @param request
     *            request对象
     * @return 响应体
     */
    public static <E> ResponseObject<E> build(Integer httpStatus, Integer code, E data, HttpServletRequest request) {
        return build(httpStatus, code, null, data, request);
    }

    /**
     * @param httpStatus
     *            HTTP代码
     * @param code
     *            业务代码
     * @param exception
     *            异常对象
     * @param request
     *            request对象
     * @return 响应体
     */
    public static <E> ResponseObject<E> build(Integer httpStatus, Integer code, Exception exception, HttpServletRequest request) {
        return build(httpStatus, code, exception, null, request);
    }

    /**
     * <p>
     * 创建响应体。
     *
     * @param httpStatus
     *            HTTP状态码
     * @param code
     *            业务代码
     * @param data
     *            伴随错误响应体一起返回的相关数据
     * @param exception
     *            发生的异常信息
     * @param request
     *            request对象
     * @return 响应体
     */
    public static <E> ResponseObject<E> build(Integer httpStatus, Integer code, Exception exception, E data, HttpServletRequest request) {
        ResponseObject<E> response = new ResponseObject<>();
        response.data = data;
        if (null != exception) {
            response.exception = exception.getClass().getCanonicalName();
        }
        if (null != request) {
            response.path = request.getRequestURI();
        }
        response.httpStatus = httpStatus;
        response.code = code;
        response.timestamp = System.currentTimeMillis();
        return response;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public boolean isSuccess() {
        return this.code == 0;
    }
}
