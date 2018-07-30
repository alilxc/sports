package com.example.sports.constant;

/**
 * 系统环境
 *
 * @author jacob
 */
public enum SysEnv {

    /**
     * 正式环境
     */
    PROD("正式环境"),

    /**
     * 演示环境
     */
    STAGE("演示环境"),

    /**
     * 开发环境
     */
    DEV("开发环境"),

    /**
     * 测试环境
     */
    TEST("测试环境");

    public String label;

    SysEnv(String label) {
        this.label = label;
    }

    public static boolean isPROD(String env) {
        return PROD.toString().equalsIgnoreCase(env);
    }

    public static boolean isSTAGE(String env) {
        return STAGE.toString().equalsIgnoreCase(env);
    }

    public static boolean isTEST(String env) {
        return TEST.toString().equalsIgnoreCase(env);
    }

    public static boolean isDEV(String env) {
        return DEV.toString().equalsIgnoreCase(env);
    }
}
