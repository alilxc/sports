package com.example.sports.constant;

/**
 * 错误码（200-599 禁止使用）
 */
public enum Errors {
    // 0-200 系统级
    SUCCESS(0, "操作成功"), //
    SYSTEM_ERROR(1, "系统错误"), // 自定义错误，可以修改label
    SYSTEM_CUSTOM_ERROR(2, "自定义错误"), //
    SYSTEM_DATA_NOT_FOUND(3, "数据不存在"), //
    SYSTEM_NOT_LOGIN(4, "请登录"), //
    SYSTEM_NO_ACCESS(5, "无权限访问"), //
    SYSTEM_REQUEST_PARAM_ERROR(6, "请求参数错误"), //
    SYSTEM_BUSINESS_ERROR(7, "系统繁忙,请您稍后再试"),

    // 用户模块(700-800)
    USER_LOGIN_REQUIRE(700, "请先登录"), //
    USER_LOGIN_ERROR(701, "用户名或密码错误"), //
    USER_PASSWORD_INPUT_ERROR(702, "密码不符合要求"), //
    USER_EXIST(703, "用户已存在"), //
    ;

    public int    code;
    public String label;

    private Errors(int code, String label) {
        this.code = code;
        this.label = label;
    }

    /**
     * 获取状态码描述
     *
     * @param code
     *            状态码
     * @return 状态码描述，如果没有返回空串
     */
    public static String getLabel(int code) {
        String result = "";
        for (Errors status : Errors.values()) {
            if (status.code == code) {
                result = status.label;
            }
        }
        return result;
    }

}
