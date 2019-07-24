package com.example.sports.constant;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-07-22 02:41
 **/
public enum SysUserEnum {

    ADMIN(3, "1,2,3,4,5","管理员"),

    LEADER(2, "2,3", "领队"),

    REFEREE(1, "2,4", "裁判");

    private int type;

    private String roles;

    private String desc;

    SysUserEnum(int type, String roles, String desc){
        this.desc = desc;
        this.type = type;
        this.roles = roles;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
