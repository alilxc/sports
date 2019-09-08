package com.example.sports.constant;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-29 00:44
 **/
public enum  StatusEnum {

    PROCEEDING(1, "比赛中"),


    FINISHED(2, "已完成"),

    PRINTED(0, "待打印");

    public int    status;
    public String desc;

    StatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static StatusEnum getByStatus(int status) {
        for (StatusEnum typeEnum : values()) {
            if (typeEnum.getStatus() == status) {
                return typeEnum;
            }
        }
        return null;
    }
}
