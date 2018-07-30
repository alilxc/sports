package com.example.sports.constant;

public enum UserStatus {


    DISABLE(1, "禁用"),

    /**
     * 
     */
    ABLE(0, "未禁用");

    public int    code;
    public String label;

    UserStatus(int code, String label) {
        this.code = code;
        this.label = label;
    }

}
