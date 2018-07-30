package com.example.sports.constant;

/**
 * 错误代码
 */
public class ErrorContants {

    /**
     * 成功
     */
    public static final int SUCCESS = 0;
    /**
     * 服务内部异常
     */
    public static final int SYSTEM_INNER_ERROR = 1001;
    /**
     * 请求参数不合法
     */
    public static final int PARAMS_INAVAILABLE = 2001;
    /**
     * 没有可用的数据 调用接口的参数找不到对应的合法数据，例如:通过用户id找不到用户信息
     */
    public static final int NO_AVALIABLE_DATA = 3001;
    /**
     * 没有权限
     */
    public static final int PERMISSION_DENY = 4001;

    /*
    * 数据库无此权限用户
    * */
    public static final int NO_PT_USER = 5001;
    /*
     * 权限用户表为空
     * */
    public static final int NO_PT = 6001;

}
